package com.java.thread.synchronize;

/**
 * 内部类的同步问题
 * @author xuweizhi
 * @date 2018/12/26 16:48
 */
public class PublicClass {

    private String username;

    private String password;

    /**
     * 外包外访问class必须声明为public,不管是子包或者
     * 同包访问不需要声明public
     */
    public class PrivateClass {

        private String age;

        private String address;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void printPublicProperty() {
            System.out.println(username + "" + password);
        }

    }



    public String getUsername() {
        class MethodClass{
            void say(){
                System.out.println("MethodClass");
            }
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {

        PublicClass publicClass = new PublicClass();
        publicClass.setUsername("usernameValue");
        publicClass.setPassword("passwordValue");
        System.out.println(publicClass.getUsername() + " " + publicClass.getPassword());

        PrivateClass privateClass = publicClass.new PrivateClass();
        privateClass.setAddress("addressValue");
        privateClass.setAge("ageValue");
        System.out.println(privateClass.getAge() + " " + privateClass.getAddress());

        OutClass outClass = new OutClass();
        outClass.say();

    }
}

class OutClass{

    void say(){
        System.out.println("你好啊");
    }
}