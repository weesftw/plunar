function onModal(button, value = null)
{
    const modal = document.getElementById(button + "Modal");
    const close = document.getElementById(button + "CloseModal");

    console.log("a");
    console.log(modal);
    console.log(close);
    console.log("??????????");

    $("main #" + button).click(function()
    {
        console.log("Calling method click change to display block.");

        if(button === "edit" || button === "delete")
        {
            console.log('#' + button + 'Modal #' + button + '_name');

            $.ajax({url: value,
                success: function(result)
                {
                    console.log(result);

                    $('#' + button + 'Modal #' + button + '_id').val(result.id);
                    $('#' + button + 'Modal #' + button + '_name').val(result.name).text(result.name);
                }});
        }

        modal.style.pointerEvents = "auto";
        modal.style.display = "block";
        modal.style.opacity = "1";
    });

    close.onclick = function ()
    {
        console.log("Calling method close.onClick() change to display none.");
        modal.style.display = "none";

        reset();
    }
}

function reset()
{
    console.log("Calling method reset() change to display none.");

    $('#editModal #edit_id').val(null);
    $('#editModal #edit_name').val(null);
}