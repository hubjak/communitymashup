/*******************************************************************************
 * Copyright (c) 2013 Peter Lachenmaier - Cooperation Systems Center Munich (CSCM).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Peter Lachenmaier - Design and initial implementation
 ******************************************************************************/
package org.sociotech.communitymashup.application.impl;

import java.io.Serializable;
import java.io.Serializable;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.ocl.conditions.BooleanOCLCondition;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.ocl.ParserException;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.ocl.conditions.BooleanOCLCondition;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.ocl.ParserException;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.ocl.conditions.BooleanOCLCondition;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.ocl.ParserException;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.ocl.conditions.BooleanOCLCondition;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.ocl.ParserException;
import org.eclipse.emf.ecore.EClassifier;
import org.sociotech.communitymashup.application.*;
import org.sociotech.communitymashup.application.ApplicationKeyConfig;
import org.sociotech.communitymashup.application.ApplicationPackage;
import org.sociotech.communitymashup.application.ConfigurableElement;
import org.sociotech.communitymashup.application.Configuration;
import org.sociotech.communitymashup.application.Database;
import org.sociotech.communitymashup.application.FEEDInterface;
import org.sociotech.communitymashup.application.Interface;
import org.sociotech.communitymashup.application.MappingRule;
import org.sociotech.communitymashup.application.Mashup;
import org.sociotech.communitymashup.application.MashupAdmin;
import org.sociotech.communitymashup.application.MashupContainer;
import org.sociotech.communitymashup.application.OAuthAdmin;
import org.sociotech.communitymashup.application.OAuthClientConfig;
import org.sociotech.communitymashup.application.OAuthClientScope;
import org.sociotech.communitymashup.application.OAuthConfig;
import org.sociotech.communitymashup.application.OCLRestrictedProperty;
import org.sociotech.communitymashup.application.Persistency;
import org.sociotech.communitymashup.application.Property;
import org.sociotech.communitymashup.application.RESTInterface;
import org.sociotech.communitymashup.application.Security;
import org.sociotech.communitymashup.application.Source;
import org.sociotech.communitymashup.application.XMLFile;
import org.sociotech.communitymashup.rest.ArgNotFoundException;
import org.sociotech.communitymashup.rest.RequestType;
import org.sociotech.communitymashup.rest.RestCommand;
import org.sociotech.communitymashup.rest.RestUtil;
import org.sociotech.communitymashup.rest.UnknownOperationException;
import org.sociotech.communitymashup.rest.WrongArgCountException;
import org.sociotech.communitymashup.rest.WrongArgException;
import org.sociotech.communitymashup.rest.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>OAuth Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sociotech.communitymashup.application.impl.OAuthConfigImpl#getClients <em>Clients</em>}</li>
 *   <li>{@link org.sociotech.communitymashup.application.impl.OAuthConfigImpl#getAdmins <em>Admins</em>}</li>
 *   <li>{@link org.sociotech.communitymashup.application.impl.OAuthConfigImpl#getUseScopeInterfaceOnRedirect <em>Use Scope Interface On Redirect</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OAuthConfigImpl extends SecurityImpl implements OAuthConfig {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2013 Peter Lachenmaier - Cooperation Systems Center Munich (CSCM).\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n \tPeter Lachenmaier - Design and initial implementation";

	/**
	 * The cached value of the '{@link #getClients() <em>Clients</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClients()
	 * @generated
	 * @ordered
	 */
	protected EList<OAuthClientConfig> clients;

	/**
	 * The cached value of the '{@link #getAdmins() <em>Admins</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdmins()
	 * @generated
	 * @ordered
	 */
	protected EList<OAuthAdmin> admins;

	/**
	 * The default value of the '{@link #getUseScopeInterfaceOnRedirect() <em>Use Scope Interface On Redirect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseScopeInterfaceOnRedirect()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean USE_SCOPE_INTERFACE_ON_REDIRECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUseScopeInterfaceOnRedirect() <em>Use Scope Interface On Redirect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseScopeInterfaceOnRedirect()
	 * @generated
	 * @ordered
	 */
	protected Boolean useScopeInterfaceOnRedirect = USE_SCOPE_INTERFACE_ON_REDIRECT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OAuthConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.OAUTH_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OAuthClientConfig> getClients() {
		if (clients == null) {
			clients = new EObjectContainmentEList<OAuthClientConfig>(OAuthClientConfig.class, this, ApplicationPackage.OAUTH_CONFIG__CLIENTS);
		}
		return clients;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OAuthAdmin> getAdmins() {
		if (admins == null) {
			admins = new EObjectContainmentEList<OAuthAdmin>(OAuthAdmin.class, this, ApplicationPackage.OAUTH_CONFIG__ADMINS);
		}
		return admins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getUseScopeInterfaceOnRedirect() {
		return useScopeInterfaceOnRedirect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUseScopeInterfaceOnRedirect(Boolean newUseScopeInterfaceOnRedirect) {
		Boolean oldUseScopeInterfaceOnRedirect = useScopeInterfaceOnRedirect;
		useScopeInterfaceOnRedirect = newUseScopeInterfaceOnRedirect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.OAUTH_CONFIG__USE_SCOPE_INTERFACE_ON_REDIRECT, oldUseScopeInterfaceOnRedirect, useScopeInterfaceOnRedirect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.OAUTH_CONFIG__CLIENTS:
				return ((InternalEList<?>)getClients()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.OAUTH_CONFIG__ADMINS:
				return ((InternalEList<?>)getAdmins()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.OAUTH_CONFIG__CLIENTS:
				return getClients();
			case ApplicationPackage.OAUTH_CONFIG__ADMINS:
				return getAdmins();
			case ApplicationPackage.OAUTH_CONFIG__USE_SCOPE_INTERFACE_ON_REDIRECT:
				return getUseScopeInterfaceOnRedirect();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ApplicationPackage.OAUTH_CONFIG__CLIENTS:
				getClients().clear();
				getClients().addAll((Collection<? extends OAuthClientConfig>)newValue);
				return;
			case ApplicationPackage.OAUTH_CONFIG__ADMINS:
				getAdmins().clear();
				getAdmins().addAll((Collection<? extends OAuthAdmin>)newValue);
				return;
			case ApplicationPackage.OAUTH_CONFIG__USE_SCOPE_INTERFACE_ON_REDIRECT:
				setUseScopeInterfaceOnRedirect((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ApplicationPackage.OAUTH_CONFIG__CLIENTS:
				getClients().clear();
				return;
			case ApplicationPackage.OAUTH_CONFIG__ADMINS:
				getAdmins().clear();
				return;
			case ApplicationPackage.OAUTH_CONFIG__USE_SCOPE_INTERFACE_ON_REDIRECT:
				setUseScopeInterfaceOnRedirect(USE_SCOPE_INTERFACE_ON_REDIRECT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ApplicationPackage.OAUTH_CONFIG__CLIENTS:
				return clients != null && !clients.isEmpty();
			case ApplicationPackage.OAUTH_CONFIG__ADMINS:
				return admins != null && !admins.isEmpty();
			case ApplicationPackage.OAUTH_CONFIG__USE_SCOPE_INTERFACE_ON_REDIRECT:
				return USE_SCOPE_INTERFACE_ON_REDIRECT_EDEFAULT == null ? useScopeInterfaceOnRedirect != null : !USE_SCOPE_INTERFACE_ON_REDIRECT_EDEFAULT.equals(useScopeInterfaceOnRedirect);
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (useScopeInterfaceOnRedirect: ");
		result.append(useScopeInterfaceOnRedirect);
		result.append(')');
		return result.toString();
	}

	/**
	 * Generates an EObjectCondition to check whether an Object is of the type OAuthConfig.
	 * 
	 * @return An EObjectCondition whether the Object is of the type OAuthConfig.
	 * @generated
	 */
	public static EObjectCondition generateIsTypeCondition() {
		return new EObjectTypeRelationCondition(ApplicationPackageImpl.eINSTANCE.getOAuthConfig());
	}
	
	/**
	 * This method provides a generic access to the Getters of this class.
 	 * 
 	 * @param opName The name of the Feature to be gotten.
 	 *
 	 * @return The value of the Feature or null.
 	 * 
	 * @generated
	 */
	protected Object getFeature(String featureName) throws UnknownOperationException {
		if ( featureName.equalsIgnoreCase("clients") )
			return this.getClients();		
		if ( featureName.equalsIgnoreCase("admins") )
			return this.getAdmins();		
		if ( featureName.equalsIgnoreCase("useScopeInterfaceOnRedirect") )
			return this.getUseScopeInterfaceOnRedirect();			
		return super.getFeature(featureName); 
	}
	
	/**
	 * This method provides a generic access to the Setters of this class.
 	 * 
 	 * @param opName The name of the Feature to be set.
 	 * @param value The new value of the feature.
 	 * 
	 * @generated
	 */
	protected Object setFeature(String featureName, Object value) throws WrongArgException, UnknownOperationException {
		if ( featureName.equalsIgnoreCase("useScopeInterfaceOnRedirect") ) {
				java.lang.Boolean fuseScopeInterfaceOnRedirect = null;
				try {
					try {
						fuseScopeInterfaceOnRedirect = (java.lang.Boolean)(RestUtil.fromInput(value));
					} catch (ClassNotFoundException e) {
						fuseScopeInterfaceOnRedirect = (java.lang.Boolean)value;
					}
				} catch (ClassCastException e) {
					throw new WrongArgException("OAuthConfig.setFeature", "java.lang.Boolean",value.getClass().getName());
				}
				this.setUseScopeInterfaceOnRedirect(fuseScopeInterfaceOnRedirect);
			return this;
			}			
		super.setFeature(featureName, value);
		return this; 
	}
	
	/**
	 * This method provides a generic access to the Operations of this class.
 	 * 
 	 * @param opName The name of the requested Operation.
 	 * @param values The arguments to be used.
 	 * 
 	 * @return The result of the Operation or null.
 	 * 
	 * @generated
	 */
	protected Object doOperation(RestCommand command) throws ArgNotFoundException, WrongArgException, WrongArgCountException, UnknownOperationException {	
		return super.doOperation(command);
	}

	/**
	 * This method can be used to recursively and generically call the Getter, Setters and Operations of the generated classes.
	 * 
	 * @param input The commands to be processed.
	 * @param requestType The HTTP-Request method.
	 * 
	 * @return The result of the Getter/Operation or null.
	 * 
	 * @generated
	 */
	@Override
	public Object process(LinkedList<RestCommand> input, RequestType requestType) throws ArgNotFoundException, WrongArgException, WrongArgCountException, UnknownOperationException {
		Object o = null;
		RestCommand c = input.poll();
		// check for HTTP-Request method
		if (requestType == RequestType.rtGet) {
			// only Getters are allowed -> side-effects...
			if (c.getCommand().startsWith("get")) {
				if (c.getArgCount() != 0) throw new WrongArgCountException(c.getCommand(), 0, c.getArgCount());
				o = this.getFeature(c.getCommand().substring(3));
			}
		} else {
			// everything is allowed - at least for now
			try {
				o = this.doOperation(c);
			} catch(Exception e) {
				if (c.getCommand().startsWith("get")) {
					if (c.getArgCount() != 0) throw new WrongArgCountException(c.getCommand(), 0, c.getArgCount());
					o = this.getFeature(c.getCommand().substring(3));
				} else if (c.getCommand().startsWith("set")) {
					if (c.getArgCount() != 1) throw new WrongArgCountException(c.getCommand(), 1, c.getArgCount());
					Object so = c.getArg("new" + c.getCommand().substring(3));
					o = this.setFeature(c.getCommand().substring(3), so);
				} else {
					if (e instanceof ArgNotFoundException)
						throw (ArgNotFoundException)e;
					if (e instanceof WrongArgException)
						throw (WrongArgException)e;
					if (e instanceof WrongArgCountException)
						throw (WrongArgCountException)e;
					if (e instanceof UnknownOperationException)
						throw (UnknownOperationException)e;
				}
			}
		}
		if (input.isEmpty()) {
			return o;
		} else { 
			if (o instanceof SourceImpl) {
				return ((Source) o).process(input, requestType);
			}
			if (o instanceof MashupImpl) {
				return ((Mashup) o).process(input, requestType);
			}
			if (o instanceof MappingRuleImpl) {
				return ((MappingRule) o).process(input, requestType);
			}
			if (o instanceof ConfigurationImpl) {
				return ((Configuration) o).process(input, requestType);
			}
			if (o instanceof PersistencyImpl) {
				return ((Persistency) o).process(input, requestType);
			}
			if (o instanceof XMLFileImpl) {
				return ((XMLFile) o).process(input, requestType);
			}
			if (o instanceof DatabaseImpl) {
				return ((Database) o).process(input, requestType);
			}
			if (o instanceof PropertyImpl) {
				return ((Property) o).process(input, requestType);
			}
			if (o instanceof OCLRestrictedPropertyImpl) {
				return ((OCLRestrictedProperty) o).process(input, requestType);
			}
			if (o instanceof MashupContainerImpl) {
				return ((MashupContainer) o).process(input, requestType);
			}
			if (o instanceof InterfaceImpl) {
				return ((Interface) o).process(input, requestType);
			}
			if (o instanceof RESTInterfaceImpl) {
				return ((RESTInterface) o).process(input, requestType);
			}
			if (o instanceof FEEDInterfaceImpl) {
				return ((FEEDInterface) o).process(input, requestType);
			}
			if (o instanceof SecurityImpl) {
				return ((Security) o).process(input, requestType);
			}
			if (o instanceof OAuthConfigImpl) {
				return ((OAuthConfig) o).process(input, requestType);
			}
			if (o instanceof ApplicationKeyConfigImpl) {
				return ((ApplicationKeyConfig) o).process(input, requestType);
			}
			if (o instanceof OAuthClientConfigImpl) {
				return ((OAuthClientConfig) o).process(input, requestType);
			}
			if (o instanceof OAuthAdminImpl) {
				return ((OAuthAdmin) o).process(input, requestType);
			}
			if (o instanceof OAuthClientScopeImpl) {
				return ((OAuthClientScope) o).process(input, requestType);
			}
			if (o instanceof MashupAdminImpl) {
				return ((MashupAdmin) o).process(input, requestType);
			}
			if (o instanceof ConfigurableElementImpl) {
				return ((ConfigurableElement) o).process(input, requestType);
			}
			if (o instanceof List) {
				return RestUtil.listProcess((List<?>) o, input, requestType);
			}
		}
		return null;
	}
} //OAuthConfigImpl
