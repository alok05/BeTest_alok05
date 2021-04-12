GET: $(document).ready(
    function() {

        $("#credit").submit(function(event) {
            event.preventDefault();
            $("#validationMessage").empty();
            ajaxGet();
        });

        // DO GET
        function ajaxGet() {
            var selectedSource = $("#dropdown").val();
            $.ajax({
                type: "GET",
                url: 'creditlimit/' + selectedSource,
                success: function(result) {
                    if (result.status == "success") {
                        var html = '<thead class="thead-light "><tr>\n' +
                            ' <th>Name</th>\n' +
                            ' <th>Address</th>\n' +
                            ' <th>Postcode</th>\n' +
                            ' <th>Phone</th>\n' +
                            ' <th>Credit Limit</th>\n' +
                            ' <th>Birthday</th>\n' +
                            ' </tr></thead>';
                        $.each(result.data,
                            function(i, personInfo) {
                                html = html + '<tr><td>' + personInfo.name + '</td>\n' +
                                    ' <td>' + personInfo.address + '</td>\n' +
                                    ' <td>' + personInfo.postCode + '</td>\n' +
                                    ' <td>' + personInfo.phoneNumber + '</td>\n' +
                                    ' <td>' + personInfo.creditLimit + '</td>\n' +
                                    ' <td>' + personInfo.birthDay + '</td>' +
                                    ' </tr>';
                                var user = "Book Name  " +
                                    personInfo.name +
                                    ", Author  = " + personInfo.address +
                                    "<br>";

                            });
                        document.getElementById("tableHeading").innerHTML = "Records from "+selectedSource+" Source";
                        document.getElementById("usersList").innerHTML = html;
                    } else {
                        $("#usersList").empty();
                        $("#tableHeading").empty();
                    }
                },
                error: function(jqXHR) {
                    $("#usersList").empty();
                    $("#tableHeading").empty();
                    var jsonObject = JSON.parse(jqXHR.responseText);
                    $("#validationMessage").css('color', 'red');
                    document.getElementById("validationMessage").innerHTML = jsonObject.data;
                }
            });
        }
    })
