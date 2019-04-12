//定义包空间
namespace java thrift.generated
namespace py py.thrift.generated

//thrift --gen java netty/src/protobuf/data.thrift生成目录指令
//定义别名
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

//struct 相当于class
struct Person {
   1:optional String username,
   2:optional int age,
   3:optional boolean married
}

exception DataException {
   1:optional String message,
   2:optional String callStack,
   3:optional String date //没有时间类型，用字符串传递数据
}

//service 相当于接口 相当于Controller TCP方式
service PersonService {

  Person getPersonByUsername(1:required String username) throws (1:DataException dataException),

  void savePerson(1:required Person person ) throws (1:DataException dataException)
}