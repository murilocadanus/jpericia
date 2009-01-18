<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:javascript formName="clienteForm" />

<div align="center" class="title"><bean:message key="cliente.consulta" bundle="cliente" /></div>

<div align="center" >

<html:errors />

<display:table 
	name="requestScope.pagingList" 
	pagesize="${requestScope.pagingPageSize}" 
	cellspacing="1" 
	requestURI="/cadastro/cliente/consultaCliente.do"
	sort="external"
	partialList="true" 
	size="${requestScope.pagingListSize}"
	id="row"
	>
  <display:column sortable="true" sortName="orderByCodigo" property="codigo" title="Codigo" />
  <display:column sortable="true" sortName="orderByName" property="nome" title="Nome" />
  <display:column sortable="false" property="sobrenome" title="Sobrenome" />
  <display:column sortable="false" property="telefone" title="Telefone" />
  <display:column sortable="false" property="email" title="E-mail" />
</display:table>




</div>
