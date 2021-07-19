package cn.codingstyle.common;

import lombok.Getter;

@Getter
public class ResponseDTO {
    private int code;
    private String message;
    private Object data;

    public ResponseDTO(Object data) {
        this.code = 200;
        this.message = "";
        this.data = data;
    }

    public ResponseDTO() {

    }

    public static ResponseDTO notFound() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.code = 404;
        responseDTO.message = "找不到数据";
        return responseDTO;
    }
}
