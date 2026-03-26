package by.tami.kinotower.web.util;

import by.tami.kinotower.web.exception.BadRequestException;

import java.util.Base64;

public class CursorUtils {

    public static String encodeCursor(long id) {
        return Base64
                .getUrlEncoder()
                .withoutPadding()
                .encodeToString(String.valueOf(id).getBytes());
    }

    public static Long decodeCursor(String cursor) {
        if (cursor == null || cursor.isEmpty()) return null;

        try {
            byte[] decodedCursor = Base64.getDecoder().decode(cursor);
            return Long.parseLong(new String(decodedCursor));
        } catch (Exception e) {
            throw new BadRequestException("CursorUtils.decodeCursor(), something went wrong when decoding cursor, cursor value is " + cursor);
        }
    }

}
