/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fiestaiot.portal.testbed.service;

import eu.fiestaiot.portal.testbed.service.dto.TestbedRegisterInputDTO;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author nguyendanghung
 */
public interface FilterService {
    

    String findTestbedByIRIAndName(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;
    String findTestbedByRegisterID(TestbedRegisterInputDTO input)throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;
    String findAllTestbedsByUserID(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;
    
    String getAllTestbedsByUserID(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;
    String findAllTestbedsRegisterIDsByUserID(TestbedRegisterInputDTO input)throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;
    String findAllTestbedsRegisterByRegisterIDList(TestbedRegisterInputDTO input)throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;
    String findTestbedByIRI(TestbedRegisterInputDTO input)throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException;

}
