<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:javascript formName="clienteForm" />

<div align="center" class="title"><bean:message key="cliente.consulta" bundle="cliente" /></div>

<div align="center" >

<html:errors />

<html:form styleClass="cssform" action="/cadastro/cliente/consultaCliente" onsubmit="">  

<html:xhtml/>

<html:hidden property="method" value="consultar" />

<p>
<label>Nome:</label>
<html:text property="nome" />
</p>

<div style="margin-left: 150px;">
<html:submit value="Enviar" styleClass="botao" /> <html:reset value="Limpar" styleClass="botao"/> 
</div>

</html:form>

</div>
