package com.andreiciubotaru.holidayplanner.controller.BusinessLayer;

class DeleteButton implements Button {
    @Override
    public void onClick(Event event) {
        event.delete();
    }

    @Override
    public void onHoover(Event event) {
        event.showTooltip();

    }
}
