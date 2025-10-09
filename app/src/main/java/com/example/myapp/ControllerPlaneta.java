package com.example.myapp;

public class ControllerPlaneta {

    DAOPlaneta dao;

    public  ControllerPlaneta() {
        dao = new DAOPlaneta();
        dao.add(new Planeta("Mercurio", R.drawable.mercury));
        dao.add(new Planeta("Venus", R.drawable.venus));
        dao.add(new Planeta("Terra", R.drawable.earth));
        dao.add(new Planeta("Marte", R.drawable.mars));
        dao.add(new Planeta("Jupter", R.drawable.jupter));
        dao.add(new Planeta("Saturno", R.drawable.saturn));
        dao.add(new Planeta("Urano", R.drawable.uranus));
        dao.add(new Planeta("Netuno", R.drawable.neptune));
    }
}
