<%@ page contentType="text/html;charset=UTF-8" %> <%@ taglib prefix="c"
uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Sobre nós" scope="request" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div
  class="-mx-4 -mt-8 mb-8 overflow-hidden rounded-b-2xl shadow-md sm:mx-0 sm:mt-0 sm:rounded-2xl"
>
  <img
    src="https://picsum.photos/seed/vinheria-header/1200/360"
    alt=""
    width="1200"
    height="360"
    class="h-56 w-full object-cover sm:h-72"
  />
</div>

<h1 class="mb-4 text-3xl font-bold text-stone-900">Sobre a Vinheria</h1>
<div class="prose prose-stone max-w-3xl space-y-4 text-stone-700">
  <p>
    A <b>Vinheria Agnello</b> nasceu como uma empresa familiar, fundada pelo Sr.
    Giulio Agnello com o objetivo de compartilhar a paixão pelo mundo dos vinhos
    e oferecer rótulos selecionados com qualidade e tradição.
  </p>
  <p>
    Hoje, ao lado de sua filha Bianca, a vinheria mantém seus valores de
    atendimento próximo, confiança e cuidado com cada cliente, proporcionando
    uma experiência acolhedora para quem aprecia bons vinhos e bons momentos.
  </p>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
