package server;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerMirrorTest {
    public static ResponseEntity<String> responseBuilder(String res){
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Test
    public void statusTest() {
        MirrorController mc = new MirrorController();
        assertEquals(mc.status(), responseBuilder("{\"status\" : \"UP\"}"));
    }

    @Test
    public void mirrorTest() {
        MirrorController mc = new MirrorController();
        assertEquals(mc.mirror("{1231}"), responseBuilder("{1231}"));
    }
}