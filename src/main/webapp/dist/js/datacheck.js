/*

==================================================================

�ַ�������

Trim(string):ȥ���ַ������ߵĿո�

==================================================================

*/


/*

==================================================================

LTrim(string):ȥ����ߵĿո�

==================================================================

*/

function LTrim(str) {

    var whitespace = new String(" \t\n\r");

    var s = new String(str);


    if (whitespace.indexOf(s.charAt(0)) != -1) {

        var j = 0, i = s.length;

        while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {

            j++;

        }

        s = s.substring(j, i);

    }

    return s;

}


/*

==================================================================

RTrim(string):ȥ���ұߵĿո�

==================================================================

*/

function RTrim(str) {

    var whitespace = new String(" \t\n\r");

    var s = new String(str);


    if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {

        var i = s.length - 1;

        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {

            i--;

        }

        s = s.substring(0, i + 1);

    }

    return s;

}


/*

==================================================================

Trim(string):ȥ��ǰ��ո�

==================================================================

*/

function Trim(str) {

    return RTrim(LTrim(str));

}


/*

IsInt(string,string,int or string):(�����ַ���,+ or - or empty,empty or 0)

���ܣ��ж��Ƿ�Ϊ����������������������������+0��������+0

*/

function IsInt(objStr, sign, zero) {

    var reg;

    var bolzero;


    if (Trim(objStr) == "") {

        return false;

    }

    else {

        objStr = objStr.toString();

    }


    if ((sign == null) || (Trim(sign) == "")) {

        sign = "+-";

    }


    if ((zero == null) || (Trim(zero) == "")) {

        bolzero = false;

    }

    else {

        zero = zero.toString();

        if (zero == "0") {

            bolzero = true;

        }

        else {

            alert("����Ƿ����0������ֻ��Ϊ(�ա�0)");

        }

    }


    switch (sign) {

        case "+-":

            //����

            reg = /(^-?|^\+?)\d+$/;

            break;

        case "+":

            if (!bolzero) {

                //������

                reg = /^\+?[0-9]*[1-9][0-9]*$/;

            }

            else {

                //������+0

                //reg=/^\+?\d+$/;

                reg = /^\+?[0-9]*[0-9][0-9]*$/;

            }

            break;

        case "-":

            if (!bolzero) {

                //������

                reg = /^-[0-9]*[1-9][0-9]*$/;

            }

            else {

                //������+0

                //reg=/^-\d+$/;

                reg = /^-[0-9]*[0-9][0-9]*$/;

            }

            break;

        default:

            alert("�����Ų�����ֻ��Ϊ(�ա�+��-)");

            return false;

            break;

    }


    var r = objStr.match(reg);

    if (r == null) {

        return false;

    }

    else {

        return true;

    }

}

