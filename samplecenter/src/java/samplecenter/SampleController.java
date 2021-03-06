package samplecenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import samplecenter.util.JsfUtil;
import samplecenter.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.Query;
import javax.servlet.http.Part;

@Named("sampleController")
@SessionScoped
public class SampleController implements Serializable {

    private Sample current;
    private DataModel items = null;
    private Part file;
    private String baseSampleFolder =  "C:";
    
    private String searchQuery;
    private int searchPage;   
    private int searchQueryCount;   

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
        
        if (file != null){
                                
            try {
                
                String file_name = UUID.randomUUID().toString() + "_" + file.getSubmittedFileName();
                // String file_url = folderName + "/samples/" + file_name;
                String file_url = baseSampleFolder + "/samples/" + file_name;
                
                current.setUrl(file_name);
                
                InputStream is = file.getInputStream();
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                
                File targetFile = new File(file_url);
                OutputStream outStream;
                outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
            } catch (IOException ex) {
                Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @EJB
    private samplecenter.SampleFacade ejbFacade;
    private PaginationHelper pagination;
    private PaginationHelper searchPagination;
    private int selectedItemIndex;
    
    @EJB
    private samplecenter.FolderFacade folderFacade;
    private Folder root;

    public SampleController() {
    }

    
    //------------------
    public Folder getRoot(){
        return root;
    }
    
    public void setRoot(Folder f){
        root = f;
        current.setFkFolder(f);
    }
    
    
    public void setUpRootFolder(){
        root = folderFacade.find(1);
        selectedFolder= root;
        root.setUpRoot();
    }
    
    private Folder selectedFolder;
    public void setSelectedFolder(Folder f)
    {
        selectedFolder = f;
    }
    public Folder getSelectedFolder()
    {
        return selectedFolder;
    }
    
    
    
    public Sample getSelected() {
        if (current == null) {
            current = new Sample();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SampleFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Sample) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String prepareView(Sample item){
        current = item;
        return "/sample/View";
    }

    public String prepareCreate() {
        current = new Sample();
        selectedItemIndex = -1;
        return "VIew";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SampleCreated"));
            return prepareView(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Sample) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SampleUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Sample) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SampleDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Sample getSample(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String pattern) {
        this.searchQuery = pattern;
        this.searchQueryCount = ejbFacade.countSearch(pattern);
        this.searchPage = 0;
    } 
    
    public int getSearchQueryCount(){
        return searchQueryCount;
    }
    
    public void setSearchQueryCount(int v){
        searchQueryCount = v;
    }
    
    public void incSearchPage(int direction){
        searchPage += direction;
    }
    
    public int getSearchPage(){
        return searchPage;
    }
    
    public List<Sample> search(String pattern){
        int max = 10;
        return ejbFacade.search(pattern, searchPage*max, max);
    }
    
    @FacesConverter(forClass = Sample.class)
    public static class SampleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SampleController controller = (SampleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sampleController");
            return controller.getSample(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sample) {
                Sample o = (Sample) object;
                return getStringKey(o.getIdsample());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sample.class.getName());
            }
        }

    }

}
