package projetofinal.gustavodariano.apprhponto;

import androidx.annotation.NonNull;

public class Employee {
    public String ID;
    public String Name;
    public String Email;


    @NonNull
    @Override
    public String toString() {
        return Name + " -  " + Email;
    }


}

