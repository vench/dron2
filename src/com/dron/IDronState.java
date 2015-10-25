/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dron;

import java.util.Map;

/**
 *
 * @author vench
 */
public interface IDronState {
    
    public String getIP();    
    public Map<String, String> getParams();
    public void setParams(Map<String, String> params);
}
