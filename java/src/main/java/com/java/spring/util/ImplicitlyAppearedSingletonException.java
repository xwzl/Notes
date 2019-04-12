package com.java.spring.util;

/**
 * (without wrapping in a {@code BeanCreationException}).
 *
 * @author Juergen Hoeller
 * @since 5.0
 */
@SuppressWarnings("serial")
public class ImplicitlyAppearedSingletonException extends IllegalStateException {

	public ImplicitlyAppearedSingletonException() {
		super("About-to-be-created singleton instance implicitly appeared through the " +
				"creation of the factory bean that its bean definition points to");
	}

}
