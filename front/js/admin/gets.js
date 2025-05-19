function fun() {
    var flag = false
    if (!flag) {
        document.getElementById("table").style.display = ""

    } else {
        document.getElementById("table").style.display = "none"
    }
    flag= !flag
}
function getDrivers(){
    fun();
    let tableBody = document.getElementById("tab");
    fetch(('http://localhost:8080/taxi/ad/drivers'), {
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
                const phoneCell = document.createElement('td');
                phoneCell.textContent = item.phone;
                row.appendChild(phoneCell);

                const fullNameCell = document.createElement('td');
                fullNameCell.textContent = item.fullname;
                row.appendChild(fullNameCell);

                const passportDataCell = document.createElement('td');
                passportDataCell.textContent = item.passport;
                row.appendChild(passportDataCell);

                const licenseNumberCell = document.createElement('td');
                licenseNumberCell.textContent = item.license;
                row.appendChild(licenseNumberCell);

                const carNameCell = document.createElement('td');
                carNameCell.textContent = item.car;
                row.appendChild(carNameCell);

                const tariffCell = document.createElement('td');
                tariffCell.textContent = item.tarif;
                row.appendChild(tariffCell);

                // прикрепляем ряд к телу таблицы
                tableBody.appendChild(row);
            });
        })

}
function getClients(){
    var flag = false
    if (!flag) {
        document.getElementById("table1").style.display = ""
    } else {
        document.getElementById("table1").style.display = "none"
    }
    let tableBody = document.getElementById("tab1");
    fetch(('http://localhost:8080/taxi/ad/clients'), {
        method: 'GET',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        }
    }) .then(response => {
        return response.json();
    })
        .then(data => {
            // проходим по каждому элементу массива данных и создаем новый ряд
            data.forEach(item => {
                const row = document.createElement('tr');

                // создаем ячейки для каждого свойства объекта
                const phoneCell = document.createElement('td');
                phoneCell.textContent = item.id;
                row.appendChild(phoneCell);

                const nameCell = document.createElement('td');
                nameCell.textContent = item.name;
                row.appendChild(nameCell);

                const passwordCell = document.createElement('td');
                passwordCell.textContent = item.password;
                row.appendChild(passwordCell);


                // прикрепляем ряд к телу таблицы
                tableBody.appendChild(row);
            });
        })
}