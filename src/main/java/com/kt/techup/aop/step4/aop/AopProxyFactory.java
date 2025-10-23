package com.kt.techup.aop.step4.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.kt.techup.aop.step4.util.Util;

/**
 * AOP 개념을 이해하는 것에 아래 코드와 문법이 중요한 것은 아닙니다.
 * step3와 비교했을 때, 실제 사용 코드들에서 어떤 점이 달라지는 지 확인하고 이해하는 것이 중요합니다.
 * 단일 책임원칙, 사용 편의성, 유지보수성, 가독성 등 개선되는 것을 확인하고 이해해야 합니다.
 *
 * **AOP 개념 이해에 있어 아래 코드의 문법을 외우는 것은 중요하지 않습니다.**
 * 중요한 것은 Step 3와 비교했을 때, 실제 사용 코드(Main.java)에서 어떤 점이 달라지는지,
 * 그리고 단일 책임 원칙, 사용 편의성, 유지보수성, 가독성 등이 어떻게 개선되는지 확인하고 이해하는 것입니다.
 *
 * AopProxyFactory의 코드들에 주석은 AI를 활용하여 남겨두었습니다.
 * 아래 코드의 문법을 외울 필요는 전혀 없습니다.
 * 눈으로 읽고 이해할 수 있을 정도면 괜찮습니다.
 * 아직 자바가 미숙한 분들은 이런게 있구나 하고 넘어가도 괜찮습니다.
 */

public class AopProxyFactory {

	/**
	 * <h3>프록시 객체 생성 메서드</h3>
	 * <p>원본 객체(target)를 입력받아, 부가 기능이 적용된 프록시 객체를 생성하여 반환합니다.</p>
	 *
	 * @param target 원본 객체 (e.g., new HumanBarista()). 이 객체는 반드시 인터페이스를 구현해야 합니다.
	 * @param <T> 원본 객체의 타입
	 * @return 부가 기능(재시도, 로깅 등)이 탑재된 프록시 객체
	 */
	public static <T> T createProxy(T target) {
		// 1. 프록시가 어떤 인터페이스를 구현해야 하는지 알아냅니다. (e.g., Barista.class)
		//    원본 객체가 구현하고 있는 모든 인터페이스를 프록시도 구현하게 됩니다.
		Class<?>[] interfaces = target.getClass().getInterfaces();

		// 2. 프록시의 모든 메서드 호출을 가로채서 처리할 "업무 메뉴얼"(InvocationHandler)을 생성합니다.
		//    이 핸들러에 원본 객체(target)를 전달하여, 나중에 실제 핵심 기능을 호출할 수 있도록 합니다.
		InvocationHandler handler = new AopInvocationHandler(target);

		// 3. JDK의 동적 프록시 생성 API를 사용합니다.
		//    `Proxy.newProxyInstance()`는 런타임에 동적으로 프록시 클래스를 생성하고 그 인스턴스를 반환합니다.
		//    - ClassLoader: 프록시 클래스를 메모리에 로딩할 때 사용합니다.
		//    - interfaces: 프록시가 구현해야 할 인터페이스 목록입니다. (이 인터페이스들의 메서드 호출이 가로채집니다.)
		//    - handler: 프록시의 메서드가 호출될 때마다 실행될 실제 로직(업무 메뉴얼)입니다. (AopInvocationHandler의 invoke 메서드)
		@SuppressWarnings("unchecked")
		T proxy = (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), interfaces, handler);

		return proxy;
	}

	/**
	 * <h3>프록시의 실제 로직 - AopInvocationHandler</h3>
	 * <p>
	 * `InvocationHandler`는 프록시 객체의 모든 메서드 호출을 가로채는 "관제탑" 역 할을 합니다.
	 * 프록시 객체의 어떤 메서드가 호출되든지, 실제로는 이 `invoke` 메서드가 대신 실행됩니다.
	 * `invoke` 메서드 안에서, 어떤 부가 기능을 어떤 순서로 적용할지 정의합니다.
	 * </p>
	 */
	private static class AopInvocationHandler implements InvocationHandler {
		private final Object target; // 프록시가 감싸고 있는 원본 객체 (e.g., HumanBarista)

		public AopInvocationHandler(Object target) {
			this.target = target;
		}

		/**
		 * 프록시의 메서드가 호출될 때마다 이 `invoke` 메서드가 실행됩니다.
		 * 이곳에서 어노테이션을 확인하고 해당 부가 기능을 적용합니다.
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// 리플렉션을 사용해, 현재 호출된 메서드(method)에 어떤 어노테이션이 붙어있는지 확인합니다.
			// 프록시의 메서드 객체가 아닌, 원본 객체의 실제 메서드 객체를 가져와야 어노테이션 정보를 얻을 수 있습니다.
			Method originalMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());

			String classAndMethodName =
				"[" + target.getClass().getSimpleName() + "::" + originalMethod.getName() + "]";

			// --- 부가 기능 적용 체인 (Chain of Responsibility) --- //
			// 데코레이터를 겹겹이 쌓는 것과 유사하게, 여러 부가 기능을 순서대로 적용합니다.
			// 여기서는 @Retryable이 가장 바깥쪽에서 예외를 처리하고, 그 안에서 @CompletionLoggable이 처리됩니다.

			// 1. 가장 바깥쪽 레이어: @Retryable 어노테이션 처리
			if (originalMethod.isAnnotationPresent(Retryable.class)) {
				int retryCount = 0;
				while (true) {
					try {
						// 재시도 로직 안에서는, 다음 책임 체인(로깅 -> 원본 호출)을 실행합니다.
						// 주의: `method.invoke(target, args)`를 직접 호출하면 무한 재귀에 빠질 수 있으므로,
						// `executeInnerChain` 헬퍼 메서드를 통해 다음 단계의 로직을 호출합니다.
						return executeInnerChain(originalMethod, args);
					} catch (InvocationTargetException e) {
						// `InvocationTargetException`은 원본 메서드가 던진 예외를 감싸고 있습니다.
						Throwable cause = e.getCause(); // 원본 예외를 추출합니다.
						retryCount++;

						System.out.println(
							classAndMethodName + "[AOP-Retry] Retrying... (" + retryCount + "/"
								+ Util.MAX_RETRY_COUNT
								+ ")");
						if (retryCount >= Util.MAX_RETRY_COUNT) {
							System.err.println(
								classAndMethodName + "[AOP-Retry] Failed after " + retryCount
									+ " retries. Original error: " + cause.getMessage());
							throw cause; // 모든 재시도 실패 시, 원본 예외를 다시 던져서 호출자에게 알립니다.
						}
					}
				}
			} else {
				// @Retryable 어노테이션이 없으면, 바로 다음 책임 체인(로깅 -> 원본 호출)을 실행합니다.
				return executeInnerChain(originalMethod, args);
			}
		}

		/**
		 * 내부 책임 체인을 실행하는 헬퍼 메서드.
		 * @CompletionLoggable 처리 및 최종 원본 메서드 호출을 담당합니다.
		 */
		private Object executeInnerChain(Method originalMethod, Object[] args) throws Throwable {
			// 2. 안쪽 레이어: @CompletionLoggable 어노테이션 처리
			if (originalMethod.isAnnotationPresent(CompletionLoggable.class)) {
				// @CompletionLoggable은 후처리(Post-processing)이므로, 원본 메서드를 먼저 실행합니다.
				Object result = null;
				try {
					result = originalMethod.invoke(target, args); // 원본 메서드 호출
				} catch (InvocationTargetException e) {
					// 원본 메서드에서 발생한 예외를 다시 던져서 상위(Retry 로직)에서 처리할 수 있도록 합니다.
					throw e; // InvocationTargetException을 그대로 던져야 Retry 로직에서 catch 가능
				}

				// 부가 기능: 원본 메서드 실행 후에 로그를 남깁니다.
				Util.LoggingCompleted(target.getClass().getSimpleName() + "::" + originalMethod.getName());

				return result;
			} else {
				// @CompletionLoggable 어노테이션이 없으면, 바로 원본 메서드를 실행합니다.
				return originalMethod.invoke(target, args);
			}
		}
	}
}
