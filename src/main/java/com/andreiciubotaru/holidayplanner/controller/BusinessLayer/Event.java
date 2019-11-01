package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

public class Event {
    public void save() {
        System.out.println("Saving successfully!");
    }

    public void delete() {
        System.out.println("Delete successfully!");
    }

    public void edit() {
        System.out.println("Edit mode started!");
    }

    public void copy() {
        System.out.println("Copy successful!");
    }

    /**
     * @TODO add tooltip
     */
    public void showTooltip() {
        System.out.println("Button tooltip!");
    }
}