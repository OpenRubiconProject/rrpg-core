package com.openrubicon.core.api.permission;

import com.openrubicon.core.api.permission.interfaces.PermissionNode;
import com.openrubicon.core.api.services.interfaces.Service;

import java.util.ArrayList;

public class PermissionNodeService implements Service {

    private ArrayList<PermissionNode> permissionNodes = new ArrayList<>();

    public PermissionNodeService(ArrayList<PermissionNode> permissionNodes) {
        this.permissionNodes = permissionNodes;
    }

    public ArrayList<PermissionNode> getPermissionNodes() {
        return permissionNodes;
    }

    @Override
    public ArrayList<String> getObservation() {
        ArrayList<String> observation = new ArrayList<>();
        for(PermissionNode permissionNode : this.getPermissionNodes())
        {
            observation.add(permissionNode.getNode());
        }
        return observation;
    }

}
