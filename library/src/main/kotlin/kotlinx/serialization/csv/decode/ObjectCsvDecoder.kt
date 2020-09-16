package kotlinx.serialization.csv.decode

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.csv.Csv

/**
 * CSV decoder for `object`s.
 *
 * Expects the name of the object (either fully-qualified class name or
 * [kotlinx.serialization.SerialName]).
 */
internal class ObjectCsvDecoder(
    csv: Csv,
    reader: CsvReader,
    parent: CsvDecoder
) : CsvDecoder(csv, reader, parent) {

    override fun decodeSequentially(): Boolean = true

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int = 0

    override fun endStructure(descriptor: SerialDescriptor) {
        val value = reader.readColumn()
        require(value == descriptor.serialName) { "Expected '${descriptor.serialName}' but was '$value'." }
        super.endStructure(descriptor)
    }
}
