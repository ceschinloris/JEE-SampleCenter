/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplecenter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author loris
 */
@Stateless
public class StyleFacade extends AbstractFacade<Style> {

    @PersistenceContext(unitName = "samplecenterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StyleFacade() {
        super(Style.class);
    }
    
}
