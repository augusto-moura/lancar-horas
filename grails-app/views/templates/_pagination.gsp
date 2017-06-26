<g:if test="${total > max}">
	<g:set var="nPaginas" value="${Math.ceil(total / max)}"/>

	<nav>
		<ul class="pagination">
			<li><a href="javascript: void 0" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>

			<g:each in="${ (1..nPaginas) }">
				<g:set var="offsetPagina" value="${(it - 1) * max}"/>
				<li><g:link controller="${controller}" action="${action}" params="${params + [offset: offsetPagina]}">${it}</g:link></li>
			</g:each>

			<li><a href="javascript: void 0" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</g:if>
