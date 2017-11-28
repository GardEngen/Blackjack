import com.sun.xml.internal.fastinfoset.util.StringArray

/**
 * Created by Åsmund Hammer on 23.11.17.
 */

//Takes all the code as input through System.in, followed by a line containing "EOF"
fun main(args: Array<String>) {

    var lc = 0;
    var wc = 0;
    var cc = 0;
    var line = readLine()

    while (line != null) {
        if(line == "EOF")
            break;

        var splitted = line.split(' ');
        if (splitted[0] == "import" ) {
            line = readLine();
            continue;
        }

        var cont = true
        splitted.forEach { str ->
            when{
                str != "" && str != "\n" && str != "}" -> {
                    cont = false
                }
            }
        }
        if(cont) {
            line = readLine();
            continue;
        }

        lc++;
        wc += wordCount(line);
        cc += charCount(line);

        line = readLine();
    }

    println("lines: $lc, words: $wc, chars: $cc");

}

fun wordCount(line: String): Int{
    var wc = 0;
    var splitted = line.split(" ", ".", "(", ")", ";", "{", "}", ":", ",");



    splitted.forEach { str ->
        when {
            str != "" /*&& str != "->" */ -> wc++;
        }
    }
    return wc;
}


fun charCount(line : String): Int{
    var cc = 0;

    var splitted = line.split(' ');

    splitted.forEach { str ->
        str.toCharArray().forEach { ch ->
            cc++;
        }
    }
    return cc;
}
