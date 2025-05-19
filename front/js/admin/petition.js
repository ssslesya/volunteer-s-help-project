
function petitionC(){
    var btn = document.getElementById("BtnDr2")
    var flag = false
    if (!flag) {
        document.getElementById("table2").style.display = ""
    } else {
        document.getElementById("table2").style.display = "none"
    }
    let tableBody = document.getElementById("tab2");
    fetch(('http://localhost:8080/taxi/ad/petition/drivers'), {
        method: 'GET',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            // проходим по каждому элементу массива данных и создаем новый ряд
            data.forEach(item => {
                const row = document.createElement('tr');

                // создаем ячейки для каждого свойства объекта
                const rideCell = document.createElement('td');
                rideCell.textContent = item.ride;
                row.appendChild(rideCell);

                const textCell = document.createElement('td');
                textCell.textContent = item.text;
                row.appendChild(textCell);

                const starCell = document.createElement('td');
                starCell.textContent = item.star;
                row.appendChild(starCell);

                // прикрепляем ряд к телу таблицы
                tableBody.appendChild(row);
            });
        })

}
function petitionD(){
    var btn = document.getElementById("BtnDr3")
    var flag = false
    if (!flag) {
        document.getElementById("table3").style.display = ""
    } else {
        document.getElementById("table3").style.display = "none"
    }
    let tableBody = document.getElementById("tab3");
    fetch(('http://localhost:8080/taxi/ad/petition/clients'), {
        method: 'GET',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            // проходим по каждому элементу массива данных и создаем новый ряд
            data.forEach(item => {
                const row = document.createElement('tr');

                // создаем ячейки для каждого свойства объекта
                const rideCell = document.createElement('td');
                rideCell.textContent = item.ride;
                row.appendChild(rideCell);

                const textCell = document.createElement('td');
                textCell.textContent = item.text;
                row.appendChild(textCell);

                const starCell = document.createElement('td');
                starCell.textContent = item.star;
                row.appendChild(starCell);

                // прикрепляем ряд к телу таблицы
                tableBody.appendChild(row);
            });
        })

}