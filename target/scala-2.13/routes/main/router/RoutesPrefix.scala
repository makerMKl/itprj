// @GENERATOR:play-routes-compiler
// @SOURCE:D:/ITSD-DT2022-Template/conf/routes
// @DATE:Mon Feb 06 20:34:58 GMT 2023


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
