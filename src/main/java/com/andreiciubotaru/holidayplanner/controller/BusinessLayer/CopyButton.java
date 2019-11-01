package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

class CopyButton implements Button {
    @Override
    public void onClick(Event event) {
        event.copy();
    }

    @Override
    public void onHoover(Event event) {
        event.showTooltip();

    }
}