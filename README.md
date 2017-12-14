Java Classic I/O (Blocking I/O) 介绍
    Streams 字节流
    java.io包提供了对字节流进行输入/输出操作的包装类以适用多种应用场景，下面对输入/输出字节流分别进行介绍
        常用输出和输入字节 流简要类
        
        
                        |————>ByteArrayOutputStream  
                        |      
                        |————>FileOutputStream       |————>BufferedOutpubStream
                        |                            |
       OutputStream ————|————>FilterOutputStream————>|————>DataOutputStream
                        |                            |
                        |————>ObjectOutputStram      |————>PrintStream
                        |
                        |————>PipedOutputStream
                        
                        
       
       
                        |————>ByteArrayInputStream  
                        |      
                        |————>FileInputStream       |————>BufferedINpubStream
                        |                           |
       InputStream  ————|————>FilterInputStream————>|
                        |                           |
                        |————>ObjectInputStram      |————>DataInputStream
                        |
                        |————>PipedInputStream
                        
                           
*       ByteArrayOutputStream/ByteArrayInputStream:ByteArrayOutputStream 可以将数据写入字节数组，
        随着数据的能够自动扩容，可以通过调用toByteArray()方法获取写入的字节数组或者通过toString()方法获取写
        入的字节的字符串表示。无须调用close()方法进行关闭。与之对应的是ByteArrayInputStream,可以将字节数组转换成输入流。    
        用处：某个第三方API使用字节流对外输出数据，这个时候就可以使用ByteArrayOutputStream
        对象将获得的字节流暂时保存至内存，不必保存到磁盘。
*       FeleOutputStream/FileInputStream: FileOutputStream 用于将数据写入文件。一般用来写入二进制字节流，如图像文件
        数据。与之对应的是FileInputStream类，用来从文件读取字节流数据。
        用处：从类名可以知晓，FileOutputStream/FileInputStream 专门用于文件I/O操作，因操作对象为字节流，
        故FileOutputStream/FileInputStream更适用于图等二进制文件操作。
        
*       FilterOutputStream/FilterInputStream: 是所有过滤输入/输出流实现类的超类。位于已存在的输入/输
        出流（基础输入/输出流）之上，将已存在的流作为其基本数据接收器，其子类通过覆写其中某些方法以实现额外的
        功能。这里使用了Decorator设计模式。
        
*       BufferedOutputStream/BufferedInputStream:行将节数据写入该缓冲类，再一次性输出。将单字节操作
        转变为批量操作字节数组，避免了逐个字节处理操作，提高了I/O处理性能。
        用处：对比FileOutputStream/FileInputStream大幅度提高了I/O操作效率与性能。
        
*       DataOutputStream/DataInputSteam: DataOutputStream 提供了直接写入原生java数据类型数据的能
        力。后续可以使用DataInputStream将数据读取到程序中并转换成对应java基础类型。
        用处：DataOutputStream/DataInputSteam能够对java原生类型直接写入再按写入顺序直接读取。常用于
        网络数据传输过程中的写入与读取。
        
*       PrintStream:字节打印流，功能很强大的一个装饰流，作为FilterInputStream的一个子类，
        在OutputStream基础上做了增强，可以方便地打印各种类型的数据。它可以自动刷新，当我们在构造
        PrintStream时指定它自动刷新，则每次调用它的pritn或println方法之后都会及时地将数据写入底层字节
        输出流中，而不用手动调用flush去刷新，还可以在构造的时候指定编码，使得字节以我们想要的编码形式写入
        底层字节输出流中，进而保存到存储介质中。还有一个特性就是PrintStream的方法从不抛出IOException。
        用处：PrintStream常用于日志输出组件的实现，无须抛出I/O异常的场景
        
*       ObjectOutputStream/ObjectInputStream:java对象字节输入/输出流,一般用来实现java的序列化功能.

*       PipedOutputStream/PipedInputStream: 通过管道读写字节流.                 
        
        
                  
        
Writer与Read字符流
    java Stream 相关的类用来处理字节流,但是不适合用来处理字符流,因为一个字节8 bit,而一个字符16 bit,字符串
    由字符组成,字符串类型天然处理的是字符而不是字节.更重要的是,字节流无法知道字符集及字符编码.java.io包提供
    了用来处理字符流的抽象类Writer与Read及相应的子类,常用的字符输出和输入字符流:
    
        
                        |————>BufferedReader ————>LineNumberReader
                        |
                        |————>CharArrayReader  
                        |      
                        |————>FilterReader  ————>BufferedOutpubStream
                        |                            
              Reader————|————>InputStreamReader ————>FileReader
                        |                            
                        |————>PipedReader
                        |
                        |————>StringReader
                        
                        
                        |————>PrintWriter   
                        |
                        |————>BufferedWriter  
                        |      
                        |————>CahrArrayReader
                        |                           
              Writer————|————>FilterWriter    
                        |                           
                        |————>OutputStreamWriter ————>FileWriter
                        |
                        |————>PipedWriter    
                        |
                        |————>StringWriter
    
        
*       BufferedWriter/BufferedReader: 行将字符数据写入或者读取到缓冲区,再一次性处理,相对于逐个字符处理,
        提高了I/O处理性能.可类比字节流BufferedOutputStream/BufferedInputStream.
        用处:增加了对字符流操作缓存能力,使其能够批量读写字符流,提高了I/O操作的效率与性能.

*       CharArrayWriter/CharArrayReader: CharArrayWriter 提供了一个字符类型数组缓存,当写入数据的时候,
        缓冲区自动增长.可以使用toCharArray()方法获取字符数组或者使用toString()方法获取字符串.类似地,
        CharArrayReader提供了字符输入流的缓存数组.这两个类可类比字节流 ByteArrayOutputStream/
        ByteArrayInputStream.
        用处:利用CharArrayWriter/CharArrayReader 能够将字符串或者字符数组转换为字符流.这种能力在很多场景
        下都是很有用处的.譬如,某个第三方API使用字符流对外输出数据,这个时候就可以使用CahrArrayWriter对象将获
        得的字节流暂时保存到内在,不必保存到磁盘.

*       OutputStreamWriter/InputStreamReader: InputStreamReader 是字符流Reader的子类,是字节输入流
        通向字符流的桥梁.你可以在构造器中重新指定字符集,如果不指定将采用底层操作系统的默认字符集.类似,
        OutputStreamWriter是字符流Writer的子类,是字符输出流通向字节流的桥梁.在调用write()方法时会调用编
        码转换器进行编码.
        
*       FileWriter/FileReader: 分别是OutputStreamWriter/InputStreamReader的子类,提供了以字符为单位
        读写文件的能力.在处理文本类型文件时,相对于FileOutputStream/FileInputStream具有更好的读写性能.
        
*       StringWriter/PipedReader: 通过管道读写字符流,类比PipedOutputStream/PipedInputStream.

*       PrintWriter: 除了提供PrintStream中的所有print方法,还提供了格式化输出字符串的能力.其方法不会抛出
        I/O异常.                                          
               
        
        
Java blocking I/O 网络通信实现
*       在网络上,数据按照有限大小的数据报(datagram)进行传输,每个数据报分为两部分:header首部和
        payload有效载荷.首部包含数据报目的地址与端口.来源地址与端口,检测数据是否被破坏的校验和用
        于保证可靠传输的各种其他管理信息.因为数据报大小有限,需要将数据分解为多个包,再在目的地重新
        组合,在传输过程中,可能有数据丢失或者损坏,这个时候需要数据重传.还有一种可能是数据包乱序到达,
        需要进行重排序.但有了Socket,就不用完成这些繁重冗杂的工作,Socket对开发人员封装了这些网络
        底层细节.
    
Java Socket实现分为客户端Socket与服务端ServerSocket.
客户端Socket的使用方式如下:
           
*       创建Socket对象,使用创建的Socket连接远程主机.

*       建立连接后,从Socket得到输入流与输出流,Socket是全双工通道,可以使用这两个流与服务器之间相互
        发送数据.
        

服务端ServerSocket的使用方式如下:
    
*       绑定一个特定的端口创建ServerSocket对象

*       使用ServerSocket的accept()方法监听这个端口的请求连接,accept()会一直阻塞直到通过某个请求
        连接与客户端建立连接,此时accept()将返回客户端与服务端的连接的Socket对象.
        
*       通过Socket对象的getInputStream()与getOutputStream()方法获取与客户端通信的输入流与输出
        流,进行通信交互.
        
*       完成交互后关闭连接.                     



Java Non-blocking I/O(NIO)介绍
    在Classic I/O库中,数据直接面向Stream写入或者读取,而在NIO库中,数据读取与写入面向的是Buffer对象,
    这种差异使性能得到了巨大提高.
    缓冲区实质上眄一个数组,java.nio库中提供了Buffer抽象类,基于该抽象类,实现了一系列Java基本类型的
    Buffer子类,Buffer类图:
                                                 
                                            Buffer
                                               | 
                                              /|\        
           ____________________________________|_________________________________  
           |           |            |          |         |          |           |               
           |           |            |          |         |          |           |
       ByteBuffer      |        DoubleBuffer   |      IntBuffer     |       ShortBuffer    
          /|\       CharBuffer            FloatBuffer           LongBuffer      
           |
    MappedByteBuffer
    
 
所有的缓冲区都具有以四个属性来提供关于其所包含的数据元素的信息.
*       容量(Capacity):缓冲区能够容纳的数据元素的最大数量.这一容量在缓冲区创建时被设定,并且永远不能被
        修改.
             
*       上界(Limit):缓冲区的第一个不能被读或写的元素.或者说,缓冲区中现存元素的计数.

*       位置(Position):下一个要被读或写的元素的索引.位置会自动由相应的get()和put()函数更新

*       标记(Mark):一个备忘位置.调用mark()来设定mark=postion.调用reset()设定position=mark.标记
        在设定前是未定义的(undefinde).

这四个属性之间总是遵循以下关系:
            0<=mark <=position <=limit <=capacity
            

Buffer主要的API:
*       public final int capacity():返回capacity

*       public final int position()返回position

*       public final Buffer position(int newPosition): 设置新的position

*       public final int limit(): 返回 新的limit

*       public final Buffer mark():将当前的position值设置为mark

*       public final Buffer reset():将position 的值设置为前一次设置的mark 值

*       public final Buffer clear():清空Buffer,将position 值设置为0，limit 设置为capacity,
        mark 值设置为无效

*       public final Buffer flip():翻转Buffer,为读Buffer 做准备。limit 设置为当前的position,
        position值为0,mark值为无效。

*       public final Buffer rewind():倒回Buffer，为重新读取Buffer做准备。Position值为0，mark
        值为无效。

*       public final int remaining()：返回Buffer中数据个数，即当前position与limit之间的数据

*       public final boolean hasRemaining():判断Buffer是否有数据。                                 

Classic I/O中的Stream是单向的,通过OutputStream实现输出流,InputStream实现输入流.而NIO中的Channel
是一个全双工通道,可以通过Channel实现同时读取与写入.

通道据的具体实现分为文件读写通道与网络读写通道.最重要的通道实现如下:

*       FileChannel:从文件中读写数据.

*       DatagramChannel:通过UDP协议读写网络数据

*       SocketChannel:通过TCP/IP协议读写网络数据,客户端连接通道

*       ServerSocketChannel:SocketChannel对应的服务端通道实现,通过监听新的TCP/IP连接,对每一个新
        的连接创建的SocketChannel.
        

Selector选择器
    Channel在Selector上注册,Selector通过不断轮询注册在其上的Channel,能够感知到Channel可读或者可写
    事件.通过这种机制,可以使用一个或者少数几个线程管理大量的网络连接.
    
                Socket          Socket          Socket          Socket                  
                  /|\             /|\             /|\             /|\    
                   |               |               |               | 
              SocketChannel  SocketChannel  SocketChannel    SocketChannel
                   |               |               |               |
                   |               |               |               |
                 _\|/_____________\|/_____________\|/_____________\|/_     
                                           | 
                                          \|/
                                        Selector
                                          /|\ 
                                           |
                                        Thread   
                                        

NIO2及Asynchronous I/O介绍
    JDK7对原有的NIO进行了大幅度的升级,我们称为NIO2.NIO2主要改进了Classic I/O中java.io.File类对文件
    操作的局限性,比如,新引入了Path及Paths,Files,Directories等工具类,支持符号链接,扩展了文件的属性支持
    类型,新的API支持文件的复制与移动等.此外,NIO2还带来了真正意义上的Asynchronous I/O(异步I/O),具体实现
    分为文件Asynchronous I/O与网络传输AsynchronousI/O.
    
1. Path介绍:Path抽象了文件路径,可看作java.is.File类的升级版.Path是NIO2中的基础类之一,
NIO2中大量的I/O操作都会用到该类.

2. Paths,Files工具类介绍
    NIO2在易用性方面很大的一个提升源于提供了一些很好用的工具类,比如Paths和Files.
    
    Paths工具类如下:
*       public static Path get(String first, String... more):将多个路径段组合成一个新的Path

*       public static Path get(URI uri):根据uri创建Path.

通过以上两个静态方法,能够很方便地构造Path对象.
    File工具类常用方法如下:
*       public static InputStream newInputStream(Path path, OpenOption... options):获得文件输入流

*       public static OutputStream newOutputStream(Path path,OpenOption... options):获得文件输出流.

*       public static SeekableByteChannel newByteChannel(Path path,Set<? extends OpenOption> options,FileAttribute<?>... attrs):获得文件通道.

*       public static DirectoryStream<Path> newDirectoryStream(Path dir):能够通过返回对象
        DirectoryStream获取迭代器Iterator.进而获取到参数目录dir下的一级目录或者文件.
        
*       public static Path CreateFile(Path path,FileAttribute<?>... attrs):创建文件,并返回所创建文件的Path对象

*       public static Path CreateDirectory(Path dir, FileAttribute<?>... attrs):创建目录,并返回创建目录的Path对象

*       public static Path createDirectories(Path dir, FileAttriBute<?>... attrs):递归创建目录,并返回所创建目录的Path对象

*       public static Path createTempFile(Path dir,String prefix,String suffix,FileAttribute<?>... attrs):创建临时文件

*       public static Path createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs):创建指定Path的符号链接

*       public static void delete(Path path):删除Path所代表的文件

*       public static boolean deleteIfExists(Path path): 如果Path所代表的文件存在,删除Path所代表的文件

*       public static Path copy(Path source,Path target,CopyOption... options):文件复制

*       public static Path move(Path source,Path target,CopyOption... options):移动文件

*       public static Path readSymbolicLink(Path link):读取符号链接文件

*       public static boolean isSameFile(Path path, Path path2):判断是否是同一个文件

*       public static boolean isReadable(Path path):判断文件是否可读

*       public static boolean isWritable(Path path):判断文件是否可写

*       public static boolean isExecutable(Path path):判断文件是否可执行.

*       public static BufferedReader newBufferedReader(Path path, Charset cs):获取文件的缓冲字节输入流

*       public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options):获取文件的缓冲字节输出流

*       public static long copy(InputStream in,Path target, CopyOption... options):从文件输入流获取文件内容实现文件复制

*       public static long copy(Path source, OutputStream out):将文件复制到文件输出流

*       public static byte[] readAllBytes(Path path):读取文件,返回文件内容字节数组

*       public static List<String> readAllLines(Path path, Charest cs):读取文件,返回文件内容字符串数组

*       public static Path write(Path path,byte[] bytes, OpenOption... option):将字节数组内容写入指定的文件

*       public static Path write(Path path,Iterable<? extends CharSequesce> lines,Charest cs,OpenOption... options):将字符串数组写入指定的文件.


WatchService接口
    java.nio.file.WatchService接口的引入是NIO2对于文件操作功能的一个很大的提升,它提供了通过应用程序患得监听操作系统文件变更
    事件的能力.通过在Path上注册所需要患得监听的事件(文件创建,文件修改,文件删除),一旦被监听的文件发生变更,应用程序能够实时感知到
    这种变化
              
                                                        