document.addEventListener('DOMContentLoaded', function() {
    var calendar = new FullCalendar.Calendar($('#calendar')[0], {
        // buttonText: {
        //     prev: '后退',
        //     next : '前进',
        //     today: '今天',
        //     month: '月视图',
        //     week: '周视图',
        //     day: '日视图',
        //     list: '日程'
        // },
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        themeSystem:"bootstrap",
        locale:'zh-cn',
        buttonIcons: false, // show the prev/next text
        weekNumbers: true,
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events
        initialView: 'dayGridMonth',
    });
    calendar.render();
});