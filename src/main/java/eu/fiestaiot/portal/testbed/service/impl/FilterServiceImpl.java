/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fiestaiot.portal.testbed.service.impl;


/**
 *
 * @author nguyendanghung
 */
import com.google.gson.GsonBuilder;
import org.apache.commons.beanutils.BeanUtils;
import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import eu.fiestaiot.portal.testbed.repository.RegisterTestbedsRepository;
import eu.fiestaiot.portal.testbed.service.FilterService;
import eu.fiestaiot.portal.testbed.service.dto.TestbedRegisterInputDTO;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

@Service("filterService")
public class FilterServiceImpl implements FilterService {

    @Inject
    private RegisterTestbedsRepository registerTestbedsRepository;

    @Override
    public String findTestbedByIRIAndName(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        RegisterTestbeds queryResult = registerTestbedsRepository.findByIriAndName(input.getIri(), input.getName());  //.findTestbedByIRIAndName(input.getIri(), input.getName());
        RegisterTestbeds filteredResult = filterByExpectedParameters(queryResult, input);
        String result = new GsonBuilder().create()
                .toJson(filteredResult);
        return result;
    }

    @Override
    public String findTestbedByRegisterID(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        RegisterTestbeds queryResult = registerTestbedsRepository.findByRegisterID(input.getRegisterID());//testbedsRegisterDAO.findTestbedByRegisterID(input.getRegisterID());
        RegisterTestbeds filteredResult = filterByExpectedParameters(queryResult, input);
        String result = new GsonBuilder().create()
                .toJson(filteredResult);
        return result;
    }

    @Override
    public String findAllTestbedsByUserID(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        List<RegisterTestbeds> queryResultAsList = registerTestbedsRepository.findAllByUserID(input.getUserID());//testbedsRegisterDAO.findAllTestbedsByUserID(input.getUserID());
        List<RegisterTestbeds> filteredResultList = filterByExpectedParameters(queryResultAsList, input);
        String result = new GsonBuilder().create()
                .toJson(filteredResultList);
        return result;
    }
    
    @Override
    public String findAllTestbedsRegisterIDsByUserID(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        List<RegisterTestbeds> queryResult  = registerTestbedsRepository.findAllByUserID(input.getUserID());
        List<RegisterTestbeds> filteredResultList = filterByExpectedParameters(queryResult, input);
        String result = new GsonBuilder().create()
                .toJson(filteredResultList);
		return result;
    }

    @Override
    public String findAllTestbedsRegisterByRegisterIDList(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        List<RegisterTestbeds> queryResultAsList  = registerTestbedsRepository.findByRegisterIDIn(input.getRegisterIDList());
		List<RegisterTestbeds> filteredQueryResultAsList = filterByExpectedParameters(queryResultAsList,input);
        String result = new GsonBuilder().create()
                .toJson(filteredQueryResultAsList);
		return result;
    }

    @Override
    public String findTestbedByIRI(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        RegisterTestbeds queryResult  = registerTestbedsRepository.findByIri(input.getIri());
		RegisterTestbeds filteredResult = filterByExpectedParameters(queryResult,input);
        String result = new GsonBuilder().create()
                .toJson(filteredResult);
		return result;
    }

    private List<RegisterTestbeds> filterByExpectedParameters(List<RegisterTestbeds> queryResultAsList,
            TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        List<RegisterTestbeds> fielteredResultList = new ArrayList<>();
        List<String> checkedFieldsList = new ArrayList<>();
        for (RegisterTestbeds eachRessult : queryResultAsList) {
            Field[] fields = eachRessult.getClass().getDeclaredFields();
            List<Field> fieldsList = Arrays.asList(fields);

            for (String expectedField : input.getExpectedFieldsAsResult()) {
                for (Field field : fieldsList) {
                    String fieldStr = field.getName();
                    if ("serialVersionUID".equals(fieldStr)) {
                        continue;
                    }
                    if("id".equals(fieldStr)){
                        checkedFieldsList.add(fieldStr);
                        continue;
                    }
                    if (expectedField.trim().toUpperCase().equals(fieldStr.trim().toUpperCase())) {
                        checkedFieldsList.add(fieldStr);
                        continue;
                    }
                    if (checkedFieldsList.contains(fieldStr)) {
                        continue;
                    }
                }
            }

            for (Field field : fieldsList) {
                String fieldStr = field.getName();
                if (!checkedFieldsList.contains(fieldStr)) {
                    field.setAccessible(Boolean.TRUE);
                    BeanUtils.setProperty(eachRessult, fieldStr, null);
                }
            }
            fielteredResultList.add(eachRessult);
        }
        return fielteredResultList;

    }

    private RegisterTestbeds filterByExpectedParameters(RegisterTestbeds queryResult, TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        Field[] fields = queryResult.getClass().getDeclaredFields();
        List<Field> fieldsList = Arrays.asList(fields);

        List<String> checkedFieldsList = new ArrayList<>();
        for (String expectedField : input.getExpectedFieldsAsResult()) {
            for (Field field : fieldsList) {
                String fieldStr = field.getName();
                if ("serialVersionUID".equals(fieldStr)) {
                    continue;
                }
                if("id".equals(fieldStr)) {
                    checkedFieldsList.add(fieldStr);
                    continue;
                }
                if (expectedField.trim().toUpperCase().equals(fieldStr.trim().toUpperCase())) {
                    checkedFieldsList.add(fieldStr);
                    continue;
                }
            }
        }
        for (Field field : fieldsList) {
            String fieldStr = field.getName();
            if (!checkedFieldsList.contains(fieldStr)) {
                field.setAccessible(Boolean.TRUE);
                BeanUtils.setProperty(queryResult, fieldStr, null);
            }
        }
        return queryResult;
    }

    @Override
    public String getAllTestbedsByUserID(TestbedRegisterInputDTO input) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        List<RegisterTestbeds> queryResultAsList = registerTestbedsRepository.findAllByUserID(input.getUserID());
        List<RegisterTestbeds> filteredQueryResultAsList = filterByExpectedParameters(queryResultAsList,input);
        String result = new GsonBuilder().create()
                .toJson(filteredQueryResultAsList);
		return result;
    }
    

}
