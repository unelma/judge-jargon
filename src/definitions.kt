typealias D6 = Int
typealias D66 = Int

// Definition texts have been taken from Judge Dredd role-playing game rule book
enum class Speak(def: String) {
    ENGINEERING("""
        11 increase microscopic quantum relay
        12 decrease photonic artificial inversion
        13 focus linear pulse interference
        14 amplify sonic flux discriminator
        15 reverse auxiliary gravimetric signal
        16 agitate nucleonic particle capacitor
        21 pacify transwarp system configuration
        22 invert reciprocating nadion effect
        23 boost magnatomic subspace disturbance
        24 nullify quantum frequency field
        25 energise verteron wavefront phenomenon
        26 intensify ambient spatial array
        31 electrify anomalous alternating emission
        32 eliminate modulated baryon domain
        33 oscillate inverted space-time coupling
        34 modulate temporal dampening stream
        35 monitor asymmetrical tetryon variance
        36 restrict atmospheric neutrino distortion
        41 connect magnetic plasma controller
        42 convert phased interface actuator
        43 modify rapid data continuum
        44 counteract ionic E-M banks
        45 balance astrophysical nano harmonic
        46 harmonise nucleonic polaron mutation
        51 reset accelerated positron invariance
        52 recalibrate anterior override seal
        53 reroute primary access container
        54 overload secondary load generator
        55 fluctuate tertiary tachyon filter
        56 concentrate backup charge safeguard
        61 extend master compression manifold
        62 redirect emergency diagnostic buffer
        63 correlate warp mass accelerator
        64 synchronise trifold nanite booster
        65 pressurise Psitronic proton transponder
        66 recollimate thermal radiation stabilizer
        """),
    TECH("""
        11 overload core binary subroutine
        12 clone auxiliary root kernel
        13 recompile polymorphic routing firewall
        14 spoof backend injection program
        15 loop direct control protocol
        16 reboot closed data script
        21 reconfigure sub boolean array
        22 unlink modular heap compiler
        23 redesign nested source enumerator
        24 extend closed bridge function
        25 remove dynamic pattern interpreter
        26 reference persistent exception code
        31 initialize pseudo memory parameter
        32 iterate public state procedure
        33 activate secure integer table
        34 interface recursive logic database
        35 reassign generic stack extension
        36 call static assembly algorithm
        41 concatenate super pool argument
        42 mask virtual access assembler
        43 patch abstract batch exception
        44 rename run-time biometric language
        45 invoke cyclomatic analogue processor
        46 override conditional broadband counter
        51 reference contravariant command environment
        52 constrain declarative configuration index
        53 evaluate directed EKT block
        54 flush dummy floating expression
        55 append parallel AJS file
        56 access global heuristic matrix
        61 bounce intrinsic operations manager
        62 fragment invariant integrated client
        63 delete local KVS point
        64 format monomorphic latency archive
        65 flash negative NTP sector
        66 install inherited output cache
    """),
    MEDICAL("""
        11 irradiate inverted neural cells
        12 excise asymmetrical basilar pathways
        13 decontaminate malignant berylite neurons
        14 transplant persisting biomimetic tissue
        15 clone periodic cardiac protoplasm
        16 balance infected reticular nerve
        21 incise swollen synaptic muscle
        22 puncture degenerate bile bone
        23 fuse obstructed cranial cyst
        24 remove restless nodular joint
        25 secure spasming endocrine gland
        26 reshape detached neurological ganglia
        31 strengthen misaligned aplastic membrane
        32 replace infected autonomic protein
        33 repair defective sympathetic enzyme
        34 divert impaired idiopathic genome
        35 bypass scarred subcutaneous tumour
        36 excite haemorrhaging fungal clot
        41 drain inflamed frontal cluster
        42 regenerate failing temporal gene
        43 resequence occluded parietal receptor
        44 massage carcinomic occipital capillary
        45 reconstruct disrupted pituitary deposit
        46 disrupt paralyzed corellium abscess
        51 stimulate weak anchilles haematoma
        52 freeze blocked aphasic cartilage
        53 magnetize deviated leutscher tissue
        54 cauterize damaged biotic cord
        55 electrify eroded mutagenic artery
        56 modulate abscessed genetic vein
        61 pressurize toxic peripheral molecules
        62 pulse foreign core valve
        63 compress contracting radial anomaly
        64 cleanse dislocated amniotic chromosome
        65 perforate deformed transient cavity
        66 inoculate mutated intrinsic lobe
    """);

    fun speak(diceResults: List<D66>): List<String> =
        diceResults.withIndex().map { (index, dieValue) ->
            definition.selectRow(dieValue).words[index]
        }

    private val definition = defineSpeak(def)

    private fun List<WordRow>.selectRow(value: D66): WordRow =
        this.zipWithNext().find { (a, b) ->
            value < a.number || value >= a.number && value < b.number
        }?.first ?: this.last()

    private class WordRow(val number: Int, val words: List<String>)

    private fun defineSpeak(def: String) = def.trimIndent().split("\n").filter { it.isNotBlank() }.map {
        it.split(" ").let { parsed ->
            WordRow(parsed[0].toInt(), parsed.drop(1))
        }
    }
}

enum class Voice {
    ALEX, FRED, SAMANTHA, VICTORIA, TESSA, KAREN, VEENA, DANIEL
}