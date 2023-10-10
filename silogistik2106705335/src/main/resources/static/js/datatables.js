$(document).ready( function () {
    $('#myTable').DataTable({
        lengthMenu: [5, 10, 20], // Set the available options for the number of entries per page
        pageLength: 5, // Set the default number of entries per page
        "columnDefs": [
            {
                "targets": [5], // Replace with the index of the column you want to modify (e.g., 0 for the first column)
                "orderable": false // Set to false to disable sorting for the specified column
            }
        ],
    });
} );