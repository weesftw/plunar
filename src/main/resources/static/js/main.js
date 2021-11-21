$(document).ready(function()
{
    $('.counter').each(function ()
    {
        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        }, {
            duration: 3000,
            easing: 'swing',
            step: function (now)
            {
                $(this).text(Math.ceil(now));
            }
        });
    });
});

function activaTab(tab)
{
    $('.nav a[href="#' + tab + '"]').tab('show');
};