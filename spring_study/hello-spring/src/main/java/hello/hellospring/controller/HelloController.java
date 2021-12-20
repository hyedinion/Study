package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");//직접 model값 전달
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){//외부에서 값을 받아옴 그래서 그냥실행하면 오류남. 8080/hello-mvc?name=xxx 라고 해줘야함
        model.addAttribute("name",name);//Model 객체에 "name"이라는 키값에 name변수를 값으로 가지는 model을 생성
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//http 프로토콜 body 부분에 data를 내가 직접 넣어서 보내주겠다 이런말임
    public String helloString(@RequestParam("name") String name){
        return "Hello"+name;//html이 아니고 그냥 문자 그대로 전송됨
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//객체를 return 하면 기본적으로 JSON형식으로 전송됨
    }


    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
