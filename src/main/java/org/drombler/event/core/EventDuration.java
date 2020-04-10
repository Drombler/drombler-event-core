/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drombler.event.core;

import org.softsmithy.lib.text.FormatException;

/**
 *
 * @author Florian
 */
public interface EventDuration {

    Appendable formatDirName(Appendable appendable) throws FormatException;
    
}
