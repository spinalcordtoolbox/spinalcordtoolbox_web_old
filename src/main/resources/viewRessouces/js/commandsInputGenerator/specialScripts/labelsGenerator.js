/**
 * Created by inf4990 on 2014-11-25.
 */


labelsGenerator = function()
{

    var output = "";
    var labels = [];

    $("#label-series input:checkbox").each(function() {
        var label = {};
        label.x     = $(this).next().val();
        label.y     = $(this).next().next().val();
        label.z     = $(this).next().next().next().val();
        label.value = $(this).next().next().next().next().val();

        labels.push(label);
    });

    var i =0;

    //alert(labels.length);

    for(i = 0; i < labels.length ; i++)
    {


        //alert(isNumber(labels[i].x));

        if(!(isNumber(labels[i].x)))
        {
            continue;
        }

        output+= labels[i].x + "," + labels[i].y + "," + labels[i].z + "," + labels[i].value ;

        if( (i+1 < labels.length) && (isNumber(labels[i+1].x)))
        {
            output+= ":";
        }
    }


    return output;
}

function isNumber(numberToTest)
{
    var isNumber_b = isFinite(numberToTest) && !isNaN(numberToTest);

    return isNumber_b;
}