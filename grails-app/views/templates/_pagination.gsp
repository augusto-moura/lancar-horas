<g:if test="${total > max}">
	<g:set var="nPaginas" value="${Math.ceil(total / max)}"/>

	<nav>
		<ul class="pagination no-margin-top">
			<li>
				<g:link controller="${controller}" action="${action}" params="${params + [offset: 0]}">
					&laquo;
				</g:link>
			</li>

			<g:each in="${(1..nPaginas)}">
				<g:set var="offsetPagina" value="${(it - 1) * max}"/>

				<li class="${((offsetatual ?: 0) as int) == (offsetPagina as int) ? 'active' : ''}">
					<g:link controller="${controller}" action="${action}" params="${params + [offset: offsetPagina]}">${it}</g:link>
				</li>
			</g:each>

			<li>
				<g:link controller="${controller}" action="${action}" params="${params + [offset: ((nPaginas - 1) * max) as Integer]}">
					&raquo;
				</g:link>
			</li>
		</ul>
	</nav>
</g:if>
