var calendar;
document.addEventListener('DOMContentLoaded', function () {
    calendar = new FullCalendar.Calendar($('#calendar')[0], {
        headerToolbar: {
            left: 'title',
            right: 'prev today next'
        },
        themeSystem: "bootstrap",
        locale: 'zh-cn',
        buttonIcons: false, // show the prev/next text
        weekNumbers: true,
        navLinks: true, // can click day/week names to navigate views
        dayMaxEvents: true, // allow "more" link when too many events
        initialView: 'dayGridMonth',
    });
    calendar.render();
});

function fillCalendar() {
    if (calendar.getEventSources().length > 0) {
        calendar.getEventSources()[0].remove();
    }

    $.ajax({
        url: 'list/search',
        data: {
            rows: 0,
            groupId: $('#groupId').val(),
            startDate: calendar.view.activeStart.Format("yyyy-MM-dd"),
            endDate: calendar.view.activeEnd.Format("yyyy-MM-dd"),
        },
        type: 'post',
        async: false,
        cached: false,
        success: function (result) {
            if (result.rows) {
                var reservations = result.rows.filter(o => o.status == 1);
                let reservationMap = groupBy(reservations, "date");
                var reservationEvents = [];
                $.each(reservationMap, function (key, val) {
                    reservationEvents.push({
                        start: key,
                        title: val.length + '人预约',
                    });
                })

                calendar.addEventSource({events: reservationEvents});
            }
        }
    });
}
