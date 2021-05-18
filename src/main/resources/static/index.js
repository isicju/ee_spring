$.get("http://localhost:8080/employees/", function (employees) {
    if (employees) {
        for (var i = 0; i < employees.length; i++) {
            $('#container').append("<tr onclick='updateData(" + employees[i].id + ")'><td>" + employees[i].id + " </td>" +
                "<td>" + employees[i].firstName + "</td>" +
                "<td>" + employees[i].lastName + " </td>" +
                "<td>" + employees[i].salary + " </td>" +
                "<td>" + employees[i].hireDate + " </td>" +
                "</tr>");
        }
        $('#background').addClass('d-none');
        $('#dataContainer').removeClass('d-none');
    }
});

var updateData = function (id) {
    $('#detailsBackground').removeClass('d-none');
    $('#details').addClass('d-none');
    $.get("http://localhost:8080/employees/details/" + id, function (employeeDetails) {
        if (employeeDetails) {
            $('#name').val(employeeDetails.firstName);
            $('#lastName').val(employeeDetails.lastName);
            $('#jobTitle').val(employeeDetails.jobTitle);
            $('#depName').val(employeeDetails.departmentName);
            $('#salary').val(employeeDetails.salary);
            $('#location').val(employeeDetails.location);
            $('#managerName').val(employeeDetails.managerName.indexOf('null') !== -1 ? 'Boss already' : employeeDetails.managerName);
            $('#employeeId').val(employeeDetails.id)
        }
        $('#detailsBackground').addClass('d-none');
        $('#details').removeClass('d-none');
    });
};

var onKeyDown = function () {
    var emailValue = $('#exampleInputEmail').val();
    $("#sendReport").attr("disabled", !(emailValue.indexOf('@') !== -1));
};

var sendEmail = function () {

    $('#emailBackground').removeClass('d-none');
    $('#details').addClass('d-none');
    $.post({
        url: 'http://localhost:8080/employees/mail/',
        data: JSON.stringify({
            email: $('#exampleInputEmail').val(),
            employeeId: $('#employeeId').val(),
            reportMessage: $('#messageId').val()
        }),
        contentType: 'application/json; charset=utf-8'
    }).done(function () {
        alert('Email was sent!');
        $('#emailBackground').addClass('d-none');
        $('#details').removeClass('d-none');
    }).fail(function () {
        alert('Error sending report!')
    })
};

var generateReport = function () {
    $('#generatingReport').removeClass('d-none');
    $('#details').addClass('d-none');
    $.post({
        url: 'http://localhost:8080/employees/report/',
        data: JSON.stringify({reportMessage: $('#messageId').val(), employeeId: $('#employeeId').val()}),
        contentType: 'application/json; charset=utf-8'
    }).done(function (data) {
        showDocument(data, "application/pdf");
        $('#generatingReport').addClass('d-none');
        $('#details').removeClass('d-none');
    })
};

function base64ToArrayBuffer(_base64Str) {
    var binaryString = window.atob(_base64Str);
    var binaryLen = binaryString.length;
    var bytes = new Uint8Array(binaryLen);
    for (var i = 0; i < binaryLen; i++) {
        bytes[i] = binaryString.charCodeAt(i);
    }
    return bytes;
}

function showDocument(_base64Str, _contentType) {
    var byte = base64ToArrayBuffer(_base64Str);
    var blob = new Blob([byte], {type: _contentType});
    window.open(URL.createObjectURL(blob), "_blank");
}


