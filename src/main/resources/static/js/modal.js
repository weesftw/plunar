function onCreate(id, value = null)
{
    const modal = document.getElementById(id + "Modal");
    const close = document.getElementById(id + "CloseModal");

     console.log("Calling onCreate()");

    modal.style.pointerEvents = "auto";
    modal.style.display = "block";
    modal.style.opacity = "1";

    if(id === "edit" || id === "delete" && value != null)
    {
        $.ajax({url: value,
            success: function(result)
            {
                console.log(result);

                $('#' + id + 'Modal #' + id + '_id').val(result.id);
                $('#' + id + 'Modal #' + id + '_name').val(result.name).text(result.name);
            }});
    }

    close.onclick = function ()
    {
        modal.style.display = "none";

        reset();
    }

    function reset()
    {
        $('#editModal #edit_id').val(null);
        $('#editModal #edit_name').val(null);
    }
}