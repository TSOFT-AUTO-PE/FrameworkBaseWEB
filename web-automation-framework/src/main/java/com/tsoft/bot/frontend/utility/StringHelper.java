package com.tsoft.bot.frontend.utility;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringHelper {

    public static String cleanFileName(String potencialNombreArchivo) {

        String result = StringUtils.trimToEmpty(potencialNombreArchivo).toLowerCase();

        result = result.replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
        result = result.replace(";", StringUtils.EMPTY).replace(":", StringUtils.EMPTY);
        result = result.replace("(", StringUtils.EMPTY).replace(")", StringUtils.EMPTY);
        result = result.replace("{", StringUtils.EMPTY).replace("}", StringUtils.EMPTY);
        result = result.replace(",", StringUtils.EMPTY).replace("\\", StringUtils.EMPTY);
        result = result.replace("\"", StringUtils.EMPTY).replace("'", StringUtils.EMPTY);

        result = result.replace('.', '-');
        result = result.replace('*', '-');
        result = result.replace(" ", "-");
        result = result.replace("_", "-");
        result = result.replace(",", "-");
        result = result.replace("---", "-");
        result = result.replace("--", "-");
        result = result.replace("--", "-");
        result = result.replace("--", "-");
        result = result.replace("#", "-");
        result = result.replace("@", "-");
        result = result.replace("%", "-");

        result = result.replace("á", "a");
        result = result.replace("é", "e");
        result = result.replace("í", "i");
        result = result.replace("ó", "o");
        result = result.replace("ú", "u");
        result = result.replace("ñ", "n");

        String clean = StringUtils.EMPTY;
        for (int i = 0; i < result.length(); i++) {
            char caracter = result.charAt(i);

            if (Character.isLetterOrDigit(caracter) || caracter == ' ' || caracter == '-') {
                clean = clean + caracter;
            }
        }

        clean = clean.replace("---", "-");
        clean = clean.replace("--", "-");
        clean = clean.replace("--", "-");
        clean = clean.replace("--", "-");

        return StringUtils.trimToEmpty(clean).toLowerCase();
    }

    public static String createFileNameFixed(String potencialNombreArchivo, int nroPalabrasDescripcion) {

        String result = cleanFileName(potencialNombreArchivo);

        String[] resultSplit = result.split("-");

        // pasan los que tienen mas de 2 caracteres o si son numeros
        Object[] splitFiltered = Arrays.stream(resultSplit).filter(item -> item.length() > 3 || NumberUtils.isNumber(item)).toArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < splitFiltered.length; i++) {
            if (i > nroPalabrasDescripcion) break;
            sb.append(" ").append(splitFiltered[i]);
        }

        result = StringUtils.trimToEmpty(sb.toString());
        result = result.replace(" ", "-");
        result = result.replace("--", "-");
        result = result.replace("--", "-");

        return StringUtils.trimToEmpty(result).toLowerCase();
    }

    public static String cleanDescripcionStep(String descripcion) {

        String result = StringUtils.trimToEmpty(descripcion);

        result = StringUtils.trimToEmpty(result).replace("[]", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("{}", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("()", StringUtils.EMPTY);

        result = StringUtils.trimToEmpty(result).replaceFirst("When", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replaceFirst("Given", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replaceFirst("And", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replaceFirst("Then", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replaceFirst("Fail", StringUtils.EMPTY);

        return StringUtils.trimToEmpty(result);
    }

    public static String cleanPlantillaPregunta(String descripcion) {

        // NOTA: el unico caracter que no se debe quitar debe ser el $ que se usa como indicador.

        String result = StringUtils.trimToEmpty(descripcion).toLowerCase();

        result = result.replaceAll("á", "a");
        result = result.replaceAll("é", "e");
        result = result.replaceAll("í", "i");
        result = result.replaceAll("ó", "o");
        result = result.replaceAll("ú", "u");
        result = result.replaceAll("ñ", "n");

        result = StringUtils.trimToEmpty(result).replace(",", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace(".", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("-", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("_", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace(";", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("#", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("@", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("!", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("?", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("¿", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("(", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace(")", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("[", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("]", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("{", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("}", StringUtils.EMPTY);

        result = StringUtils.trimToEmpty(result).replace("   ", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("  ", StringUtils.EMPTY);

        return StringUtils.trimToEmpty(result);
    }

    public static String cleanParaComparar(String descripcion) {

        // NOTA: el unico caracter que no se debe quitar debe ser el $ que se usa como indicador.

        String result = StringUtils.trimToEmpty(descripcion).toLowerCase();

        result = result.replaceAll("á", "a");
        result = result.replaceAll("é", "e");
        result = result.replaceAll("í", "i");
        result = result.replaceAll("ó", "o");
        result = result.replaceAll("ú", "u");
        result = result.replaceAll("ñ", "n");

        result = StringUtils.trimToEmpty(result).replace(",", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace(".", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("-", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("_", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace(";", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("#", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("@", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("!", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("?", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("¿", StringUtils.SPACE);

        result = StringUtils.trimToEmpty(result).replace("(", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace(")", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("[", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("]", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("{", StringUtils.EMPTY);
        result = StringUtils.trimToEmpty(result).replace("}", StringUtils.EMPTY);

        result = result.replace("\n", StringUtils.EMPTY);

        result = StringUtils.trimToEmpty(result).replace("   ", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("  ", StringUtils.SPACE);

        result = StringUtils.trimToEmpty(result).replace("   ", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("  ", StringUtils.SPACE);

        result = StringUtils.trimToEmpty(result).replace("   ", StringUtils.SPACE);
        result = StringUtils.trimToEmpty(result).replace("  ", StringUtils.SPACE);

        return StringUtils.trimToEmpty(result);
    }


    public static boolean equalsSimple(String valor01, String valor02) {
        // metodo que compara la igual de dos cadenas, quitando los espacios en blanco internos, quitando todo caracter raro e ignorando el case
        return StringUtils.equalsIgnoreCase(StringHelper.cleanParaComparar(valor01), StringHelper.cleanParaComparar(valor02));
    }

    public static String maskTarjeta(String tarjeta) {

        // si ya contiene mascara, devuelve el mismo
        if (StringUtils.contains(tarjeta, "*")) return tarjeta;
        if (StringUtils.isBlank(tarjeta)) return StringUtils.EMPTY;

        String result = StringUtils.trimToEmpty(tarjeta);

        if (StringUtils.length(result) > 17) {
            return StringUtils.substring(result, 0, result.length() - 3) + "***";
        } else if (StringUtils.length(result) > 12) {
            return StringUtils.substring(result, 0, 6) + "******" + StringUtils.substring(result, 12);
        }

        return StringUtils.substring(result, 0, result.length() - 3) + "***";
    }
}
