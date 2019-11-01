package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

class SaveButton implements Button {

    @Override
    public void onClick(Event event) {
        event.save();
    }

    @Override
    public void onHoover(Event event) {
        event.showTooltip();
    }
}
