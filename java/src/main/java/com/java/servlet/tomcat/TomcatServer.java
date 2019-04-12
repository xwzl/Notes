package com.java.servlet.tomcat;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;

/**
 *
 * https://www.jianshu.com/p/62ec977996df
 *
 * http://www.ibm.com/developerworks/cn/java/j-lo-tomcat1/
 *
 * Tomcat 的心脏是两个组件：<b>Connector 和 Container</b>
 * <p>
 * Connector 组件是可以被替换，这样可以提供给服务器设计者更多的选择，因为这个组件是如此重要，不仅跟服务器的设计的本身，而且和不
 * 同的应用场景也十分相关，所以一个 Container 可以选择对应多个 Connector。多个 Connector 和一个 Container 就形成了一个
 * Service，Service 的概念大家都很熟悉了，有了 Service 就可以对外提供服务了，但是 Service 还要一个生存的环境，必须要有人能
 * 够给她生命、掌握其生死大权，那就非 Server 莫属了。所以整个 Tomcat 的生命周期由 Server 控制。
 * <h2>以 Service 作为“婚姻”</h2>
 * 我们将 Tomcat 中 Connector、Container 作为一个整体比作一对情侣的话，Connector 主要负责对外交流，可以比作为 Boy，Container
 * 主要处理 Connector 接受的请求，主要是处理内部事务，可以比作为 Girl。那么这个 Service 就是连接这对男女的结婚证了。是 Service 将
 * 它们连接在一起，共同组成一个家庭。当然要组成一个家庭还要很多其它的元素。
 * <p>
 * 说白了，Service 只是在 Connector 和 Container 外面多包一层，把它们组装在一起，向外面提供服务，一个 Service 可以设置多个 Connector，
 * 但是只能有一个 Container 容器。这个 Service 接口的方法列表如下：
 * <p>
 * Tomcat 中 Service 接口的标准实现类是 StandardService 它不仅实现了 Service 借口同时还实现了 Lifecycle 接口，
 * 这样它就可以控制它下面的组件的生命周期了。
 * <p>
 * StandardService 主要实现了{@link org.apache.catalina.core.StandardService#setContainer(Engine)}和
 * {@link StandardService#addConnector(Connector)}方法
 * <p>
 * 这段代码很简单，其实就是先判断当前的这个 Service 有没有已经关联了 Container，如果已经关联了，那么去掉这个关联关系——
 * oldContainer.setService(null)。如果这个 oldContainer 已经被启动了，结束它的生命周期。然后再替换新的关联、再初始
 * 化并开始这个新的 Container 的生命周期。最后将这个过程通知感兴趣的事件监听程序。这里值得注意的地方就是，修改 Container
 * 时要将新的 Container 关联到每个 Connector，还好 Container 和 Connector 没有双向关联，不然这个关联关系将会很难维护。
 * <p>
 * 上面是 addConnector 方法，这个方法也很简单，首先是设置关联关系，然后是初始化工作，开始新的生命周期。这里值得一提的是，注意 Connector 用的是数
 * 组而不是 List 集合，这个从性能角度考虑可以理解，有趣的是这里用了数组但是并没有向我们平常那样，一开始就分配一个固定大小的数组，它这里的实现机制是：
 * 重新创建一个当前大小的数组对象，然后将原来的数组对象 copy 到新的数组中，这种方式实现了类似的动态数组的功能，这种实现方式，值得我们以后拿来借鉴。
 *
 * <h2>以 Server 为“居”</h2>
 * 前面说一对情侣因为 Service 而成为一对夫妻，有了能够组成一个家庭的基本条件，但是它们还要有个实体的家，这是它们在社会上生存之本，
 * 有了家它们就可以安心的为人民服务了，一起为社会创造财富。
 * <p>
 * Server 要完成的任务很简单，就是要能够提供一个接口让其它程序能够访问到这个 Service 集合、同时要维护它所包含的所有 Service 的
 * 生命周期，包括如何初始化、如何结束服务、如何找到别人要访问的 Service。还有其它的一些次要的任务，如您住在这个地方要向当地政府去登
 * 记啊、可能还有要配合当地公安机关日常的安全检查什么的。
 * <p>
 * 它的标准实现类<b>StandardServer</b>实现了上面这些方法，同时也实现了Lifecycle、MbeanRegistration 两个接口的所有方法，下面主要
 * 看一下 StandardServer 重要的一个方法 {@link org.apache.catalina.core.StandardServer#addService(Service)}的实现.
 *
 * <h2>组件的生命线“Lifecycle”</h2>
 * 前面一直在说 Service 和 Server 管理它下面组件的生命周期，那它们是如何管理的呢？
 * <p>
 * Tomcat 中组件的生命周期是通过 Lifecycle 接口来控制的，组件只要继承这个接口并实现其中的方法就可以统一被拥有它的组件控制了，这样一层一层的
 * 直到一个最高级的组件就可以控制 Tomcat 中所有组件的生命周期，这个最高的组件就是 Server，而控制 Server 的是 Startup，也就是您启动和关闭
 * Tomcat。
 * <p>
 * Lifecycle 接口的方法的实现都在其它组件中，就像前面中说的，组件的生命周期由包含它的父组件控制，所以它的 Start 方法自然就是调用它下面的组件的
 * Start 方法，Stop 方法也是一样。如在 Server 中 Start 方法就会调用 Service 组件的 Start 方法，Server 的 Start 方法
 * {@link StandardServer#start()}
 *
 * <h2>Connector 组件</h2>
 * Connector 组件是 Tomcat 中两个核心组件之一，它的主要任务是负责接收浏览器的发过来的 tcp 连接请求，创建一个 Request 和 Response 对象
 * 分别用于和请求端交换数据，然后会产生一个线程来处理这个请求并把产生的 Request 和 Response 对象传给处理这个请求的线程，处理这个请求的线程就
 * 是 Container 组件要做的事了。
 *
 * @author xuweizhi
 * @date 2019/03/19 11:33
 */
public class TomcatServer {

    //@Override
    //public void setContainer(Engine engine) {
    //    Engine oldEngine = this.engine;
    //    if (oldEngine != null) {
    //        oldEngine.setService(null);
    //    }
    //    this.engine = engine;
    //    if (this.engine != null) {
    //        this.engine.setService(this);
    //    }
    //    if (getState().isAvailable()) {
    //        if (this.engine != null) {
    //            try {
    //                this.engine.start();
    //            } catch (LifecycleException e) {
    //                log.warn(sm.getString("standardService.engine.startFailed"), e);
    //            }
    //        }
    //        // Restart MapperListener to pick up new engine.
    //        try {
    //            mapperListener.stop();
    //        } catch (LifecycleException e) {
    //            log.warn(sm.getString("standardService.mapperListener.stopFailed"), e);
    //        }
    //        try {
    //            mapperListener.start();
    //        } catch (LifecycleException e) {
    //            log.warn(sm.getString("standardService.mapperListener.startFailed"), e);
    //        }
    //        if (oldEngine != null) {
    //            try {
    //                oldEngine.stop();
    //            } catch (LifecycleException e) {
    //                log.warn(sm.getString("standardService.engine.stopFailed"), e);
    //            }
    //        }
    //    }
    //}

    //@Override
    //public void addConnector(Connector connector) {
    //
    //    synchronized (connectorsLock) {
    //        connector.setService(this);
    //        Connector results[] = new Connector[connectors.length + 1];
    //        System.arraycopy(connectors, 0, results, 0, connectors.length);
    //        results[connectors.length] = connector;
    //        connectors = results;
    //
    //        if (getState().isAvailable()) {
    //            try {
    //                connector.start();
    //            } catch (LifecycleException e) {
    //                log.error(sm.getString(
    //                        "standardService.connector.startFailed",
    //                        connector), e);
    //            }
    //        }
    //
    //        // Report this property change to interested listeners
    //        support.firePropertyChange("connector", null, connector);
    //    }
    //
    //}


    //@Override
    //public void addService(Service service) {
    //
    //    service.setServer(this);
    //
    //    synchronized (servicesLock) {
    //        Service results[] = new Service[services.length + 1];
    //        System.arraycopy(services, 0, results, 0, services.length);
    //        results[services.length] = service;
    //        services = results;
    //
    //        if (getState().isAvailable()) {
    //            try {
    //                service.start();
    //            } catch (LifecycleException e) {
    //                // Ignore
    //            }
    //        }
    //
    //        // Report this property change to interested listeners
    //        support.firePropertyChange("service", null, service);
    //    }
    //
    //}

    //@Override
    //public final synchronized void start() throws LifecycleException {
    //
    //    if (LifecycleState.STARTING_PREP.equals(state) || LifecycleState.STARTING.equals(state) ||
    //            LifecycleState.STARTED.equals(state)) {
    //
    //        if (log.isDebugEnabled()) {
    //            Exception e = new LifecycleException();
    //            log.debug(sm.getString("lifecycleBase.alreadyStarted", toString()), e);
    //        } else if (log.isInfoEnabled()) {
    //            log.info(sm.getString("lifecycleBase.alreadyStarted", toString()));
    //        }
    //
    //        return;
    //    }
    //
    //    if (state.equals(LifecycleState.NEW)) {
    //        init();
    //    } else if (state.equals(LifecycleState.FAILED)) {
    //        stop();
    //    } else if (!state.equals(LifecycleState.INITIALIZED) &&
    //            !state.equals(LifecycleState.STOPPED)) {
    //        invalidTransition(Lifecycle.BEFORE_START_EVENT);
    //    }
    //
    //    try {
    //        setStateInternal(LifecycleState.STARTING_PREP, null, false);
    //        startInternal();
    //        if (state.equals(LifecycleState.FAILED)) {
    //            // This is a 'controlled' failure. The component put itself into the
    //            // FAILED state so call stop() to complete the clean-up.
    //            stop();
    //        } else if (!state.equals(LifecycleState.STARTING)) {
    //            // Shouldn't be necessary but acts as a check that sub-classes are
    //            // doing what they are supposed to.
    //            invalidTransition(Lifecycle.AFTER_START_EVENT);
    //        } else {
    //            setStateInternal(LifecycleState.STARTED, null, false);
    //        }
    //    } catch (Throwable t) {
    //        // This is an 'uncontrolled' failure so put the component into the
    //        // FAILED state and throw an exception.
    //        handleSubClassException(t, "lifecycleBase.startFail", toString());
    //    }
    //}

}
