function clockRun() {
    const phoneAmPm = document.querySelector(".device-header-left-ampm");
    const phoneClock = document.querySelector(".device-header-left-time");
    const phoneDate = document.querySelector(".device-header-left-date");

    setInterval(() => { 
        const now = new Date();
        const nowHours = now.getHours();
        const nowMinutes = now.getMinutes() < 10 ? `0${now.getMinutes()}` : now.getMinutes();
        const nowClockText = `${nowHours}:${nowMinutes}`;
        phoneClock.innerHTML = nowClockText;
        
        const nowAmPmText = now.getHours() >= 12 ? "오후" : "오전";
        phoneAmPm.innerHTML = nowAmPmText;

        const month = now.getMonth() + 1;
        const date = now.getDate();
        const dayOfWeekNumber = now.getDay(); 
        const week = ["일", "월", "화", "수", "목", "금", "토"];
        const dayOfWeekText = week[dayOfWeekNumber];
        phoneDate.innerHTML = `${month}월 ${date}일 ${dayOfWeekText}요일`;
    }, 1000);
}

clockRun();