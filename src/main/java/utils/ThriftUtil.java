///*
// * DeviceServerException.java
// *
// * Copyright (C) KAI Square Pte Ltd
// */
//package utils;
//
//import java.lang.reflect.*;
//import java.util.Map;
//import org.apache.thrift.TProcessor;
//import org.apache.thrift.TServiceClient;
//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.protocol.TProtocol;
//import org.apache.thrift.server.TServer;
//import org.apache.thrift.server.TThreadPoolServer;
//import org.apache.thrift.server.TThreadedSelectorServer;
//import org.apache.thrift.transport.*;
//
///**
// * Convenience class containing Apache Thrift related utility methods.
// *
// * @author Tan Yee Fan
// */
//public class ThriftUtil {
//
//    public static final int SELECTOR_THREAD_MIN = 1;
//    public static final int SELECTOR_THREAD_DEFAULT = 2;
//    public static final int SELECTOR_THREAD_MAX = 4;
//    public static final int WORKER_THREAD_MIN = 2;
//    public static final int WORKER_THREAD_DEFAULT = 5;
//    public static final int WORKER_THREAD_MAX = 10;
//
//    /**
//     * Maximum Thrift server read buffer size.
//     */
//    private static final int SERVER_MAX_READ_BUFFER_SIZE = 5000000;
//
//    /**
//     * Private constructor.
//     */
//    private ThriftUtil() {
//    }
//
//    /**
//     * Constructs a new Thrift service server and returns it. The returned
//     * server will be ready to serve Thrift service clients.
//     *
//     * @param processor Thrift processor.
//     * @param serviceServerPort Thrift service server port.
//     * @param selectorThreads
//     * @param workerThreads
//     * @param listenerThreadName
//     * @return
//     * @throws TTransportException If an error occurred when setting up the
//     *                             server.
//     */
//    public static TServer newServiceServer(TProcessor processor, int serviceServerPort,
//					   int selectorThreads, int workerThreads,
//					   String listenerThreadName) throws TTransportException {
//	TNonblockingServerSocket socket = new TNonblockingServerSocket(serviceServerPort);
//	TThreadedSelectorServer.Args serverArgs = new TThreadedSelectorServer.Args(socket);
//	serverArgs.processor(processor);
//	serverArgs.protocolFactory(new TBinaryProtocol.Factory());
//	serverArgs.maxReadBufferBytes = SERVER_MAX_READ_BUFFER_SIZE;
//
//	//By default, selectorThreads is 2, workerThreads is 5
//	serverArgs.selectorThreads = selectorThreads;
//	serverArgs.workerThreads(workerThreads);
//
//	final TServer server = new TThreadedSelectorServer(serverArgs);
//	Thread thread = new Thread() {
//
//	    @Override
//	    public void run() {
//		server.serve();
//	    }
//	};
//	if (null != listenerThreadName && !listenerThreadName.isEmpty()) {
//	    thread.setName(listenerThreadName);
//	}
//	thread.start();
//	return server;
//    }
//
//    public static TServer newServiceServer(TProcessor processor, int serviceServerPort) throws TTransportException {
//	String listenerThreadName = "";
//	return newServiceServer(processor, serviceServerPort, 2, 5, listenerThreadName);
//    }
//
//    /**
//     * New thrift server with a thread name for the listening thread.
//     *
//     * @param processor
//     * @param serviceServerPort
//     * @param listenerThreadName
//     * @return
//     * @throws TTransportException
//     */
//    public static TServer newServiceServer(TProcessor processor, int serviceServerPort, String listenerThreadName) throws TTransportException {
//	return newServiceServer(processor, serviceServerPort, 2, 5, listenerThreadName);
//    }
//
//    /**
//     * New thrift server with a thread name for the listening thread,
//     * the number of selectorThreads and workerThreads.
//     *
//     * @param processor
//     * @param serviceServerPort
//     * @param threadName
//     * @param selectorThreads
//     * @param workerThreads
//     * @return
//     * @throws TTransportException
//     */
//    public static TServer newServiceServer(TProcessor processor, int serviceServerPort,
//					   String threadName, int selectorThreads, int workerThreads) throws TTransportException {
//	return newServiceServer(processor, serviceServerPort, selectorThreads, workerThreads, threadName);
//    }
//
//    /**
//     * Constructs a new SSL encrypted Thrift service server and returns it. The returned
//     * server will be ready to serve Thrift service clients.
//     *
//     * @param processor Thrift processor.
//     * @param serviceServerPort Thrift service server port.
//     * @param sslParams SSL parameters
//     * @return
//     * @throws TTransportException If an error occurred when setting up the
//     *                             server.
//     */
//    public static TServer newSSLServiceServer(TProcessor processor, int serviceServerPort, Map<String, String> sslParams) throws TTransportException {
//	String storePath = sslParams.get("keyStore");
//	String keyPass = sslParams.get("keyPass");
//	String keyManagerType = sslParams.get("keyManagerType");
//	String keyStoreType = sslParams.get("keyStoreType");
//
//	TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
//	params.setKeyStore(storePath, keyPass, keyManagerType, keyStoreType);
//	TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(serviceServerPort, 0, null, params);
//	TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
//	serverArgs.processor(processor);
//	serverArgs.protocolFactory(new TBinaryProtocol.Factory());
//
//	final TServer server = new TThreadPoolServer(serverArgs);
//	Thread thread = new Thread() {
//
//	    @Override
//	    public void run() {
//		server.serve();
//	    }
//	};
//	thread.start();
//	return server;
//    }
//
//    /**
//     * Constructs a new Thrift service client and returns it. The returned
//     * client will be ready to connect to the Thrift service server.
//     *
//     * @param <T>
//     * @param serviceIfaceClass Thrift service interface class, as in
//     * {@code ExampleService.Iface.class}.
//     * @param serviceClientClass Thrift service client class, as in
//     * {@code ExampleService.Client.class}.
//     * @param serviceServerHost Thrift server hostname or IP address.
//     * @param serviceServerPort Thrift server port.
//     * @param socketTimeout Thrift socket timeout, in milliseconds.
//     * @return
//     * @throws TTransportException If an error occurred when setting up the
//     *                             client.
//     */
//    public static <T> Client<T> newServiceClient(Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//						 String serviceServerHost, int serviceServerPort, int socketTimeout) throws TTransportException {
//	return newServiceClient(serviceIfaceClass, serviceClientClass,
//		serviceServerHost, serviceServerPort, socketTimeout, 1, 0);
//    }
//
//    /**
//     * Constructs a new Thrift service client and returns it. The returned
//     * client will be ready to connect to the Thrift service server.
//     *
//     * @param <T>
//     * @param serviceIfaceClass Thrift service interface class, as in
//     * {@code ExampleService.Iface.class}.
//     * @param serviceClientClass Thrift service client class, as in
//     * {@code ExampleService.Client.class}.
//     * @param serviceServerHost Thrift server hostname or IP address.
//     * @param serviceServerPort Thrift server port.
//     * @param socketTimeout Thrift socket timeout, in milliseconds.
//     * @param numTries Number of tries to attempt a remote procedure call before
//     * giving up.
//     * @param retryDelay Delay between two successive tries, in milliseconds.
//     * @return
//     * @throws TTransportException If an error occurred when setting up the
//     *                             client.
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> Client<T> newServiceClient(Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//						 String serviceServerHost, int serviceServerPort, int socketTimeout, int numTries, int retryDelay) throws TTransportException {
//	if (!serviceIfaceClass.isInterface() || serviceIfaceClass == TServiceClient.class) {
//	    throw new TTransportException("Not a Thrift service interface class.");
//	}
//	if (serviceClientClass.isInterface() || !TServiceClient.class.isAssignableFrom(serviceClientClass)) {
//	    throw new TTransportException("Not a Thrift service client class.");
//	}
//	InnerClient<T> client = new InnerClient<T>();
//	AbstractHandler<T> handler = new AbstractHandler<T>(client, serviceIfaceClass, serviceClientClass, serviceServerHost, serviceServerPort, socketTimeout, numTries, retryDelay);
//	T iface = (T) Proxy.newProxyInstance(serviceClientClass.getClassLoader(), new Class[]{serviceIfaceClass}, handler);
//	client.setIface(iface);
//	return client;
//    }
//
//    /**
//     * Constructs a new SSL encrypted Thrift service client and returns it. The returned
//     * client will be ready to connect to the Thrift service server.
//     *
//     * @param <T>
//     * @param serviceIfaceClass Thrift service interface class, as in
//     * {@code ExampleService.Iface.class}.
//     * @param serviceClientClass Thrift service client class, as in
//     * {@code ExampleService.Client.class}.
//     * @param serviceServerHost Thrift server hostname or IP address.
//     * @param serviceServerPort Thrift server port.
//     * @param socketTimeout Thrift socket timeout, in milliseconds.
//     * @param numTries Number of tries to attempt a remote procedure call before
//     * giving up.
//     * @param retryDelay Delay between two successive tries, in milliseconds.
//     * @param sslParams
//     * @return
//     * @throws TTransportException If an error occurred when setting up the
//     *                             client.
//     */
//    public static <T> Client<T> newSSLServiceClient(Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//						    String serviceServerHost, int serviceServerPort, int socketTimeout,
//						    int numTries, int retryDelay, Map<String, String> sslParams) throws TTransportException {
//	if (!serviceIfaceClass.isInterface() || serviceIfaceClass == TServiceClient.class) {
//	    throw new TTransportException("Not a Thrift service interface class.");
//	}
//	if (serviceClientClass.isInterface() || !TServiceClient.class.isAssignableFrom(serviceClientClass)) {
//	    throw new TTransportException("Not a Thrift service client class.");
//	}
//	InnerClient<T> client = new InnerClient<T>();
//
//	String storePath = sslParams.get("trustStore");
//	String trustPass = sslParams.get("trustPass");
//	String trustManagerType = sslParams.get("trustManagerType");
//	String trustStoreType = sslParams.get("trustStoreType");
//	SSLHandler<T> handler = new SSLHandler<T>(client, serviceIfaceClass, serviceClientClass, serviceServerHost, serviceServerPort, socketTimeout, numTries, retryDelay, storePath, trustPass, trustManagerType, trustStoreType);
//	T iface = (T) Proxy.newProxyInstance(serviceClientClass.getClassLoader(), new Class[]{serviceIfaceClass}, handler);
//	client.setIface(iface);
//	return client;
//    }
//
//    /**
//     * Instantiates a new Thrift service client and returns it. The returned
//     * client will be ready to connect to the Thrift service server.
//     *
//     * @param serviceIfaceClass Thrift service interface class, as in
//     * {@code ExampleService.Iface.class}.
//     * @param serviceClientClass Thrift service client class, as in
//     * {@code ExampleService.Client.class}.
//     * @param serviceServerHost Thrift server hostname or IP address.
//     * @param serviceServerPort Thrift server port.
//     * @param socketTimeout Thrift socket timeout, in milliseconds.
//     * @throws TTransportException If an error occurred when setting up the
//     *                             client.
//     */
//    private static <T> InnerClient<T> instantiateClient(Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//							String serviceServerHost, int serviceServerPort, int socketTimeout) throws TTransportException {
//	if (!serviceIfaceClass.isInterface() || serviceIfaceClass == TServiceClient.class) {
//	    throw new TTransportException("Not a Thrift service interface class.");
//	}
//	if (serviceClientClass.isInterface() || !TServiceClient.class.isAssignableFrom(serviceClientClass)) {
//	    throw new TTransportException("Not a Thrift service client class.");
//	}
//	TTransport transport = null;
//	try {
//	    Constructor<? extends T> constructor = serviceClientClass.getConstructor(TProtocol.class);
//	    TSocket socket = new TSocket(serviceServerHost, serviceServerPort, socketTimeout);
//	    transport = new TFramedTransport(socket);
//	    transport.open();
//	    TProtocol protocol = new TBinaryProtocol(transport);
//	    T iface = constructor.newInstance(protocol);
//	    InnerClient<T> client = new InnerClient<T>();
//	    client.setIface(iface);
//	    client.setTransport(transport);
//	    return client;
//	} catch (TTransportException e) {
//	    if (transport != null) {
//		transport.close();
//	    }
//	    throw e;
//	} catch (Exception e) {
//	    if (transport != null) {
//		transport.close();
//	    }
//	    throw new TTransportException("Unable to instantiate service client.", e);
//	}
//    }
//
//    /**
//     * Instantiates a new SSL encryped Thrift service client and returns it. The returned
//     * client will be ready to connect to the Thrift service server.
//     *
//     * @param serviceIfaceClass Thrift service interface class, as in
//     * {@code ExampleService.Iface.class}.
//     * @param serviceClientClass Thrift service client class, as in
//     * {@code ExampleService.Client.class}.
//     * @param serviceServerHost Thrift server hostname or IP address.
//     * @param serviceServerPort Thrift server port.
//     * @param socketTimeout Thrift socket timeout, in milliseconds.
//     * @throws TTransportException If an error occurred when setting up the
//     *                             client.
//     */
//    private static <T> InnerClient<T> instantiateSSLClient(Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//							   String serviceServerHost, int serviceServerPort, int socketTimeout,
//							   String trustStore, String trustPass, String trustManagerType, String trustStoreType) throws TTransportException {
//	if (!serviceIfaceClass.isInterface() || serviceIfaceClass == TServiceClient.class) {
//	    throw new TTransportException("Not a Thrift service interface class.");
//	}
//	if (serviceClientClass.isInterface() || !TServiceClient.class.isAssignableFrom(serviceClientClass)) {
//	    throw new TTransportException("Not a Thrift service client class.");
//	}
//	TTransport transport = null;
//	try {
//	    Constructor<? extends T> constructor = serviceClientClass.getConstructor(TProtocol.class);
//	    TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
//	    params.setTrustStore(trustStore, trustPass, trustManagerType, trustStoreType);
//	    transport = TSSLTransportFactory.getClientSocket(serviceServerHost, serviceServerPort, socketTimeout, params);
//	    TProtocol protocol = new TBinaryProtocol(transport);
//	    T iface = constructor.newInstance(protocol);
//	    InnerClient<T> client = new InnerClient<T>();
//	    client.setIface(iface);
//	    client.setTransport(transport);
//	    return client;
//	} catch (TTransportException e) {
//	    if (transport != null) {
//		transport.close();
//	    }
//	    throw e;
//	} catch (Exception e) {
//	    if (transport != null) {
//		transport.close();
//	    }
//	    throw new TTransportException("Unable to instantiate service client.", e);
//	}
//    }
//
//    /**
//     * A Thrift service client.
//     *
//     * @param <T>
//     */
//    public static interface Client<T> {
//
//	/**
//	 * Returns the Thrift service interface.
//	 *
//	 * @return
//	 */
//	public T getIface();
//
//	/**
//	 * Closes the client.
//	 */
//	public void close();
//    }
//
//    private static class InnerClient<T> implements Client<T> {
//
//	private T iface;
//	private TTransport transport;
//
//	public InnerClient() {
//	    this.iface = null;
//	    this.transport = null;
//	}
//
//	@Override
//	public T getIface() {
//	    return this.iface;
//	}
//
//	public void setIface(T iface) {
//	    this.iface = iface;
//	}
//
//	public TTransport getTransport() {
//	    return this.transport;
//	}
//
//	public void setTransport(TTransport transport) {
//	    this.transport = transport;
//	}
//
//	@Override
//	public void close() {
//	    if (this.transport != null) {
//		this.transport.close();
//	    }
//	}
//    }
//
//    /**
//     * Invocation handler.
//     */
//    private static class AbstractHandler<T> implements InvocationHandler {
//
//	private InnerClient client;
//	private Class<T> ifaceClass;
//	private Class<? extends T> clientClass;
//	private String host;
//	private int port;
//	private int numTries;
//	private int timeout;
//	private int delay;
//	private T targetIface;
//
//	public AbstractHandler(InnerClient<T> client, Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//		       String serviceServerHost, int serviceServerPort, int socketTimeout, int numTries, int retryDelay) {
//	    this.client = client;
//	    this.ifaceClass = serviceIfaceClass;
//	    this.clientClass = serviceClientClass;
//	    this.host = serviceServerHost;
//	    this.port = serviceServerPort;
//	    this.timeout = socketTimeout;
//	    this.numTries = numTries;
//	    this.delay = retryDelay;
//	    this.targetIface = null;
//	}
//
//	@Override
//	public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//	    Throwable cause = null;
//	    for (int i = 0; i < this.numTries; i++) {
//		if (this.targetIface == null) {
//		    try {
//			InnerClient<T> innerClient = instantiateClient(
//				this.ifaceClass, this.clientClass, this.host, this.port, this.timeout);
//			this.client.setTransport(innerClient.getTransport());
//			this.targetIface = innerClient.getIface();
//		    } catch (TTransportException e) {
//			cause = e;
//		    }
//		}
//		if (this.targetIface != null) {
//		    try {
//			Object result = method.invoke(this.targetIface, args);
//			return result;
//		    } catch (InvocationTargetException e) {
//			cause = e.getCause();
//			if (!(cause instanceof TTransportException)) {
//			    throw cause;
//			}
//		    }
//		}
//		this.client.close();
//		this.targetIface = null;
//		if (i < numTries - 1) {
//		    try {
//			Thread.sleep(delay);
//		    } catch (InterruptedException e) {
//		    }
//		}
//	    }
//	    throw cause;
//	}
//    }
//
//    /**
//     * Invocation handler.
//     */
//    private static class SSLHandler<T> implements InvocationHandler {
//
//	private InnerClient client;
//	private Class<T> ifaceClass;
//	private Class<? extends T> clientClass;
//	private String host;
//	private int port;
//	private int numTries;
//	private int timeout;
//	private int delay;
//	private T targetIface;
//	private String trustStore;
//	private String trustPass;
//	private String trustManagerType;
//	private String trustStoreType;
//
//	public SSLHandler(InnerClient<T> client, Class<T> serviceIfaceClass, Class<? extends T> serviceClientClass,
//			  String serviceServerHost, int serviceServerPort, int socketTimeout, int numTries, int retryDelay,
//			  String trustStore, String trustPass, String trustManagerType, String trustStoreType) {
//	    this.client = client;
//	    this.ifaceClass = serviceIfaceClass;
//	    this.clientClass = serviceClientClass;
//	    this.host = serviceServerHost;
//	    this.port = serviceServerPort;
//	    this.timeout = socketTimeout;
//	    this.numTries = numTries;
//	    this.delay = retryDelay;
//	    this.targetIface = null;
//	    this.trustStore = trustStore;
//	    this.trustPass = trustPass;
//	    this.trustManagerType = trustManagerType;
//	    this.trustStoreType = trustStoreType;
//	}
//
//	@Override
//	public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//	    Throwable cause = null;
//	    for (int i = 0; i < this.numTries; i++) {
//		if (this.targetIface == null) {
//		    try {
//			InnerClient<T> innerClient = instantiateSSLClient(
//				this.ifaceClass, this.clientClass, this.host, this.port,
//				this.timeout, this.trustStore, this.trustPass, this.trustManagerType, this.trustStoreType);
//			this.client.setTransport(innerClient.getTransport());
//			this.targetIface = innerClient.getIface();
//		    } catch (TTransportException e) {
//			cause = e;
//		    }
//		}
//		if (this.targetIface != null) {
//		    try {
//			Object result = method.invoke(this.targetIface, args);
//			return result;
//		    } catch (InvocationTargetException e) {
//			cause = e.getCause();
//			if (!(cause instanceof TTransportException)) {
//			    throw cause;
//			}
//		    }
//		}
//		this.client.close();
//		this.targetIface = null;
//		if (i < numTries - 1) {
//		    try {
//			Thread.sleep(delay);
//		    } catch (InterruptedException e) {
//		    }
//		}
//	    }
//	    throw cause;
//	}
//    }
//}
