var ajaxUrl = "ajax/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    $('#filter').submit(function () {
        filter();
        return false;
    });
});

//filter ajax
function filter() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

//clear filters
$('#cancel-filter').on('click', function () {
    $('#startDate').val(null).trigger("change");
    $('#endDate').val(null).trigger("change");
    $('#startTime').val(null).trigger("change");
    $('#endTime').val(null).trigger("change");
    $.get(ajaxUrl, updateTableByData)
});