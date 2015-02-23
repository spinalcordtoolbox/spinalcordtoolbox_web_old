/**
 * Created by  by Youssef - youssef.khairallah@gmail.com on 2014-11-25.
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
    var hasEmptyValue = false;

    //alert(labels.length);

    for(i = 0; i < labels.length ; i++)
    {

        var lol = labels[i].x ;
        //alert(isNumber(labels[i].x));

        if(!(isNumber(labels[i].x)) || !(isNumber(labels[i].y)) || !(isNumber(labels[i].z)) || !(isNumber(labels[i].value)))
        {
            continue;
        }

        output+= labels[i].x + "," + labels[i].y + "," + labels[i].z + "," + labels[i].value ;

        if( (i+1 < labels.length) && isNumber(labels[i+1].x) && isNumber(labels[i+1].y) &&  isNumber(labels[i+1].z) && isNumber(labels[i+1].value))
        {
            output+= ":";
        }
    }

    return output;
}

function isNumber(numberToTest)
{
    var isNumber_b = isFinite(numberToTest) && !isNaN(numberToTest) && (numberToTest.length > 0);

    return isNumber_b;
}