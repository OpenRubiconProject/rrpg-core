package com.openrubicon.core.api.services;

import com.openrubicon.core.api.services.interfaces.Service;

import java.util.ArrayList;

public class ServiceManager {

    private static ArrayList<Service> services = new ArrayList<>();

    public void create(Service service)
    {
        services.add(service);
    }

    public ArrayList<Service> getServices()
    {
        return services;
    }

    public  boolean contains(Class serviceClass)
    {
        for(Service service : services)
        {
            if(service.getClass() == serviceClass)
                return true;
        }

        return false;
    }

    public <T extends Service> T getSerivce(Class<T> serviceClass)
    {
        for(Service service : services)
        {
            if(service.getClass() == serviceClass)
                return (T)service;
        }

        return null;
    }



}
