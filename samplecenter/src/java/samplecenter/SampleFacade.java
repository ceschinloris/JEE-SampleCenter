/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplecenter;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author loris
 */
@Stateless
public class SampleFacade extends AbstractFacade<Sample> {

    @PersistenceContext(unitName = "samplecenterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SampleFacade() {
        super(Sample.class);
    }

    public List<Sample> search(String title) {
        Query query = em.createNamedQuery("Sample.search").setParameter("title", title);
        return (List<Sample>) query.getResultList();
    }
    
}
