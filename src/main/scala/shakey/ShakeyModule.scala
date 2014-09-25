package shakey

import shakey.config.ShakeyConfig
import scala.io.Source
import org.apache.tapestry5.ioc.annotations.{Contribute,Symbol}
import org.apache.tapestry5.ioc.services.{FactoryDefaults, SymbolProvider}
import org.apache.tapestry5.ioc.MappedConfiguration
import shakey.internal.XmlLoader

/**
 * Created by jcai on 14-9-25.
 */
object ShakeyModule {
  def buildShakeConfig(@Symbol(ShakeyConstants.SERVER_HOME) serverHome:String):ShakeyConfig={
    val filePath = serverHome + "/config/shakey.xml"
    val content = Source.fromFile(filePath, ShakeyConstants.UTF8_ENCODING).mkString
    val config = XmlLoader.parseXML[ShakeyConfig](content, xsd = Some(getClass.getResourceAsStream("/shakey/shakey.xsd")))
    config
  }
  @Contribute(classOf[SymbolProvider])
  @FactoryDefaults
  def provideFactoryDefaults(configuration: MappedConfiguration[String, AnyRef]) {
    configuration.add(ShakeyConstants.SERVER_HOME, "support")
  }
}
