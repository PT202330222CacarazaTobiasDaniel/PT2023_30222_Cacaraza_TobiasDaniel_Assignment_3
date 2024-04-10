package start;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import Model.Client;
import dataAccessLayer.ClientDAO;
import presentation.Controller;

/**
 * Main Class for starting !
 * @Author: Cacaraza Tobias-Daniel
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: May 21, 2023
 */
public class Start {
    public static void main(String[] args)  {
        new Controller();
    }

}
