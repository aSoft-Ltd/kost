@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import symphony.Form
import symphony.FormInitialzer
import symphony.FormConfig
import kotlin.js.JsExport

class TaxForm(
    heading: String = "Tax provider form",
    details: String = "Add new tax provider",
    config: FormConfig,
    initializer: FormInitialzer<Tax, Tax>
) : Form<TaxFields, Tax, Tax>(
    heading, details, TaxFields(), config, initializer
)