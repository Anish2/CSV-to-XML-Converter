<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Qualified Contestants</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Name</th>
      <th>Score</th>
	  <th>Occupation</th>
	  <th>Institution</th>
    </tr>
    <xsl:for-each select="root/csv__to__xml.Contestant">
    <xsl:sort select="name"/>
    <xsl:if test="score &gt; 89">
    <tr>
      <td><xsl:value-of select="name"/></td>
      <td><xsl:value-of select="score"/></td>
	  <td><xsl:value-of select="major"/></td>
	  <td><xsl:value-of select="institution"/></td>
    </tr>
    </xsl:if>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>