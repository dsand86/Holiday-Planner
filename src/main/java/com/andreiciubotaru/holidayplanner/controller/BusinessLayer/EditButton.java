package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

class EditButton implements Button {
    @Override
    public void onClick(Event event) {
        event.edit();
    }

    @Override
    public void onHoover(Event event) {
        event.showTooltip();

    }
}
